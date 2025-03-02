package com.pos_main.Service.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.pos_main.Dto.PaginatedResponseDto;

public class HttpReqRespUtils {

	private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	public static Map<String, String> getSearchParameters(WebRequest webRequest) {
		Map<String, String[]> params = webRequest.getParameterMap();
		Map<String, String> searchParams = new HashMap<>();
		StringBuilder stringBuilder;
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			stringBuilder = new StringBuilder();
			String key = entry.getKey();
			for (String val : entry.getValue()) {
				stringBuilder.append(val);
			}
			searchParams.put(key, stringBuilder.toString());
		}
		return searchParams;
	}

	/**
	 * PaginatedResponseDtoUtil for commonly used PaginatedResponseDto response
	 * 
	 * @param returnedDomainObject
	 * @param pageNumber
	 * @param pageSize
	 * @param recordCount
	 * @return PaginatedResponseDto
	 */
	public static PaginatedResponseDto paginatedResponseMapper(List<?> returnedDomainObject, int pageNumber,
			int pageSize, int recordCount) {
		PaginatedResponseDto paginatedResponseDto = null;
		if (returnedDomainObject != null && !returnedDomainObject.isEmpty()) {
			paginatedResponseDto = new PaginatedResponseDto();
			paginatedResponseDto.setPageNumber(pageNumber);
			paginatedResponseDto.setPageSize(pageSize);
			paginatedResponseDto.setTotalRecords(recordCount);
		} else if (recordCount > 0 && recordCount < (pageSize * pageNumber)) {
			paginatedResponseDto = new PaginatedResponseDto();
			paginatedResponseDto.setPageNumber(pageNumber);
			paginatedResponseDto.setPageSize(pageSize);
			paginatedResponseDto.setTotalRecords(recordCount);
			paginatedResponseDto.setPayload(null);
		}
		return paginatedResponseDto;
	}

	public static String getClientIpAddressIfServletRequestExist() {

		if (RequestContextHolder.getRequestAttributes() == null) {
			return "0.0.0.0";
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		for (String header : IP_HEADER_CANDIDATES) {
			String ipList = request.getHeader(header);
			if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
				String ip = ipList.split(",")[0];
				return ip;
			}
		}

		return request.getRemoteAddr();
	}
}