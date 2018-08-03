package com.jiea.bull.service;

import java.util.Set;

public interface AuthorizationService {

    Set<String> getUserPermissions(Long userId);

    Set<String> getUserRoles(Long userId);
}
