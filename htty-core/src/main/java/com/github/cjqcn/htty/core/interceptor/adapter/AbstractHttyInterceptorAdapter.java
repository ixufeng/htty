package com.github.cjqcn.htty.core.interceptor.adapter;

import com.github.cjqcn.htty.core.common.BasicPathMatcher;
import com.github.cjqcn.htty.core.common.PathMatcher;
import com.github.cjqcn.htty.core.interceptor.HttyInterceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: chenjinquan
 * @create: 2018-10-08 21:07
 **/
public abstract class AbstractHttyInterceptorAdapter implements HttyInterceptorAdapter {

	private static final int DEFAULT_PRIORITY = 1024;

	private static final Set<String> DEFAULT_INCLUDE_URL_PATTENRNS =
			Arrays.asList("/**").stream().collect(Collectors.toSet());

	private static final Set<String> DEFAULT_EXCLUDE_URL_PATTENRNS = Collections.EMPTY_SET;

	private static final PathMatcher DEFAULT_PATH_MATCHER = BasicPathMatcher.instance;

	private Integer priority;

	private Set<String> includeUrlPatterns;

	private Set<String> excludeUrlPatterns;

	private PathMatcher pathMatcher;

	public AbstractHttyInterceptorAdapter() {
		priority = DEFAULT_PRIORITY;
		pathMatcher = BasicPathMatcher.instance;
	}

	@Override
	public int getPriority() {
		if (priority == null) {
			return DEFAULT_PRIORITY;
		}
		return priority;
	}

	@Override
	public Set<String> getIncludeUrlPatterns() {
		if (includeUrlPatterns == null) {
			return DEFAULT_INCLUDE_URL_PATTENRNS;
		}
		return includeUrlPatterns;
	}

	@Override
	public Set<String> getExcludeUrlPatterns() {
		if (excludeUrlPatterns == null) {
			return DEFAULT_EXCLUDE_URL_PATTENRNS;
		}
		return excludeUrlPatterns;
	}

	@Override
	public PathMatcher getPathMatcher() {
		if (pathMatcher == null) {
			return DEFAULT_PATH_MATCHER;
		}
		return pathMatcher;
	}

	public AbstractHttyInterceptorAdapter setPriority(int priority) {
		this.priority = priority;
		return this;
	}

	public AbstractHttyInterceptorAdapter includeUrlPatterns(String... urlPatterns) {
		if (urlPatterns != null) {
			for (String urlPattern : urlPatterns) {
				addIncludeUrlPattern(urlPattern);
				removeExcludeUrlPattern(urlPattern);
			}
		}
		return this;
	}

	public AbstractHttyInterceptorAdapter excludeUrlPatterns(String... urlPatterns) {
		if (urlPatterns != null) {
			for (String urlPattern : urlPatterns) {
				addExcludeUrlPattern(urlPattern);
				removeIncludeUrlPattern(urlPattern);
			}
		}
		return this;
	}

	private void addIncludeUrlPattern(String urlPattern) {
		if (includeUrlPatterns == null) {
			includeUrlPatterns = new LinkedHashSet<>();
		}
		includeUrlPatterns.add(urlPattern);
	}

	private void addExcludeUrlPattern(String urlPattern) {
		if (excludeUrlPatterns == null) {
			excludeUrlPatterns = new LinkedHashSet<>();
		}
		excludeUrlPatterns.add(urlPattern);
	}


	private void removeIncludeUrlPattern(String urlPattern) {
		if (includeUrlPatterns != null && !includeUrlPatterns.isEmpty()) {
			includeUrlPatterns.remove(urlPattern);
		}
	}

	private void removeExcludeUrlPattern(String urlPattern) {
		if (excludeUrlPatterns != null && !excludeUrlPatterns.isEmpty()) {
			excludeUrlPatterns.remove(urlPattern);
		}
	}


	@Override
	public HttyInterceptor get() {
		return this;
	}
}
