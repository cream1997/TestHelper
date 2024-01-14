package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.mapper.mock.MockRemoteUserMapper;
import com.cream.helper.obj.entity.account.User;
import com.cream.helper.obj.entity.mock.MockRemoteUser;
import com.cream.helper.obj.vo.Ret;
import com.cream.helper.obj.vo.ServerItem;
import com.cream.helper.service.IGameLoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 提供模拟数据的服务
 */
@MockComponent
public class MockGameLoginService implements IGameLoginService {

    private final MockRemoteUserMapper mockRemoteUserMapper;

    @Autowired
    public MockGameLoginService(MockRemoteUserMapper mockRemoteUserMapper) {
        this.mockRemoteUserMapper = mockRemoteUserMapper;
    }

    @Override
    public User getRemoteUser(String username) {
        MockRemoteUser mockRemoteUser = mockRemoteUserMapper.getMockRemoteUser(username);
        if (mockRemoteUser == null) {
            return null;
        } else {
            return new User(mockRemoteUser);
        }
    }

    @Override
    public User registerRemote(String username, String password) {
        MockRemoteUser mockRemoteUser = new MockRemoteUser(username, password);
        mockRemoteUserMapper.insert(mockRemoteUser);
        return new User(mockRemoteUser);
    }

    /**
     * 抓取可用的服务器列表
     */
    @Override
    public Ret<List<ServerItem>> fetchServerList() {
        ServerItem dev = new ServerItem("研发服", "192.168.1.1", 6666);
        ServerItem beta = new ServerItem("beta服", "192.168.2.2", 8888);
        ServerItem trunk = new ServerItem("稳定服", "192.168.3.3", 6688);
        List<ServerItem> serverList = new ArrayList<>(Arrays.asList(dev, beta, trunk));
        return Ret.ok(serverList);
    }

}
