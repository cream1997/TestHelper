package com.cream.helper.service.impl.mock;

import com.cream.helper.mapper.mock.MockRemoteUserMapper;
import com.cream.helper.obj.entity.account.User;
import com.cream.helper.obj.entity.mock.MockRemoteUser;
import com.cream.helper.service.IRemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 提供模拟数据的服务
 */
@Service
public class MockRemoteUserService implements IRemoteUserService {

    private final MockRemoteUserMapper mockRemoteUserMapper;

    @Autowired
    public MockRemoteUserService(MockRemoteUserMapper mockRemoteUserMapper) {
        this.mockRemoteUserMapper = mockRemoteUserMapper;
    }

    @Override
    public User getRemoteUser(String username) {
        MockRemoteUser mockRemoteUser = mockRemoteUserMapper.getMockRemoteUser(username);
        return new User(mockRemoteUser);
    }

    @Override
    public User registerRemote(String username, String password) {
        MockRemoteUser mockRemoteUser = new MockRemoteUser(username, password);
        mockRemoteUserMapper.insert(mockRemoteUser);
        return new User(mockRemoteUser);
    }
}
