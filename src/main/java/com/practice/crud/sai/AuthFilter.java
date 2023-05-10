/**
*  __        __    ____                     _
*  \ \      / /__ / __|   _  __ _ _ __ __| |
*   \ \ /\ / / _ \ |  _| | | |/ _` | '__/ _` |
*    \ V  V /  __/ |_| | |_| | (_| | | | (_| |
*     \_/\_/ \___|\____|\__,_|\__,_|_|  \__,_|
*
*   =================================================================================
*   This file is a part of "WeGuard", Copyright (C) 2022, Wenable Inc. - All Rights Reserved.
*   Wenable Inc. licenses this file to you under the WEGUARD ("WeGuard") LICENSE, Version 0.7 (the "License")
*   =================================================================================
*   You may not use this file except in compliance with the "License".
*   A copy of the "License" (license.txt) is shipped along with this "WeGuard" source code bundle.
*   In case you are unable to locate the "License",  it is crucial that you obtain a copy by contacting Wenable Inc.
*
*   CR/PR				Reason								Responsible		URL
* ---------------------------------------------------------------------------------------------------------------------
*	WGSUPPRTL-369		Added License Info					Subhayan		https://wenable.atlassian.net/browse/WGSUPPRTL-369
* ---------------------------------------------------------------------------------------------------------------------



package com.practice.crud.sai;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.weguard.support.server.beans.TokenBean;
import com.weguard.support.server.dao.SupportRestPathDao;
import com.weguard.support.server.dao.SupportRoleAccessDao;
import com.weguard.support.server.dao.SupportUserDao;
import com.weguard.support.server.generic.response.DataResult;
import com.weguard.support.server.models.SupportRestAPIPathInfo;
import com.weguard.support.server.models.SupportRoleAccessInfo;
import com.weguard.support.server.services.UserService;
import com.weguard.support.server.utils.AppConstants;
import com.weguard.support.server.utils.GSONHelper;
import com.weguard.support.server.utils.RestAPIPathInfoComparator;
import com.weguard.support.server.utils.TokenAuthentication;
import com.weguard.support.server.utils.TokenUtils;

@Component
@Order(2)
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	TokenUtils utils;

	@Autowired
	UserService userService;

	@Autowired
	SupportUserDao supportDao;

	@Autowired
	SupportRestPathDao restPathDao;

	@Autowired
	SupportRoleAccessDao roleAccessDao;

	@Autowired
	GSONHelper gsonHelper;
	private static final Logger logger = LogManager.getLogger();

	private static List<String> exemptedList;
	private static Gson gson = new Gson();

	public AuthFilter() {
		openAPIs();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestedURI = httpRequest.getRequestURI();
		String requestType = httpRequest.getMethod();
		boolean authVerified = false;
		TokenBean token = null;
		String role = null;

		// check for the open APIs
		authVerified = isOpenAPI(requestedURI);
		if (authVerified) {
			filterChain.doFilter(request, response);
			return;
		}
		// check Bearer token and validate

		String accessToken = request.getHeader(AppConstants.AUTHORIZATION_HEADER);
		if (accessToken != null) {
			accessToken = accessToken.split(" ")[1].trim();
			token = utils.decodeToken(accessToken);
			if (token != null) {
				if (token.getUserIp() != null) {
					authVerified = true;
				}
				role = token.getRole();
			}
		}
		if (role != null && role.equals(AppConstants.SUPER_ADMIN)) {
			logger.info("super admin logging in :{}", token.getEmail());
			if (token.getUserIp() != null) {
				authVerified = true;
			}
		} else {
			Vector<Pattern> pathInfoPatterns = null;
			/**
			 * If the role is not super admin we are checking for the API permissions in the
			 * else part for all other roles
			
			authVerified = false;
			if (token != null) {
				// Getting all the permissions of the logged in role
				SupportRoleAccessInfo accessInfo = roleAccessDao.getByRole(token.getRole());
				logger.info(" logging in user :{} role :{}", token.getEmail(), accessInfo.getRoleName());
				if (accessInfo != null) {
					List<SupportRestAPIPathInfo> restApis = restPathDao.getAll();
					Collections.sort(restApis, new RestAPIPathInfoComparator());
					pathInfoPatterns = new Vector<>();
					Collections.sort(restApis, new RestAPIPathInfoComparator());
					for (int count = 0; count < restApis.size(); count++) {
						pathInfoPatterns.addElement(Pattern.compile(restApis.get(count).getRestService()));
					}

					for (int count = 0; count < restApis.size(); count++) {
						Pattern pathInfoPattern = pathInfoPatterns.get(count);
						SupportRestAPIPathInfo api = restApis.get(count);
						Matcher matcher = pathInfoPattern.matcher(requestedURI);
						if ((requestedURI.trim().contains(api.getRestService().trim()) || matcher.find())
								&& api.getRestServiceType().trim().equalsIgnoreCase(requestType.trim())) {
							logger.info("API match found for this user :{}", token.getEmail());
							List<String> permissions = accessInfo.getPermissions();
							// checking for the permissions of the support person role
							for (String permission : permissions) {
								if ((permission.equalsIgnoreCase(api.getPermissionNeeded()))) {
									logger.info("API is allowed for this user :{}", token.getEmail());
									authVerified = true;
									break;
								}
							}
						}

					}
				}
			}
		}
		if (!authVerified)

		{
			logger.info("invalid access token :{}", accessToken);
			getAccessDeniedResponse(httpResponse, response.getWriter());
			return;
		}
		SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(token));
		filterChain.doFilter(request, response);
	}

	private boolean isOpenAPI(String uri) {
		for (String string : exemptedList) {
			if (uri.contains(string)) {
				return true;
			}
		}
		return false;
	}

	private void openAPIs() {
		exemptedList = new ArrayList<String>();
		exemptedList.add(AppConstants.APPLICATION_REST_BASE_PATH + "/user/login");
		exemptedList.add(AppConstants.APPLICATION_BASE_PATH + "/swagger");
		exemptedList.add(AppConstants.APPLICATION_BASE_PATH + "/webjars");
		exemptedList.add(AppConstants.APPLICATION_BASE_PATH + "/images");
		exemptedList.add(AppConstants.APPLICATION_BASE_PATH + "/v2");
		exemptedList.add(AppConstants.APPLICATION_BASE_PATH + "/csrf");
		exemptedList.add(AppConstants.APPLICATION_REST_BASE_PATH + "/v2/user/email-user");
		exemptedList.add(AppConstants.APPLICATION_REST_BASE_PATH + "/app/version");
		exemptedList.add(AppConstants.APPLICATION_REST_BASE_PATH + "/user/logout/username");
		exemptedList.add(AppConstants.APPLICATION_REST_BASE_PATH + "/role/access/all");

	}

	private void getAccessDeniedResponse(HttpServletResponse httpResponse, PrintWriter printWriter) {
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		printWriter.println(gson.toJson(new DataResult<>(false, "Unauthorized request", 401)));
		printWriter.close();
	}

}
*/