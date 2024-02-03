package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.config.configuration.exception.Err;
import com.cream.helper.constant.GamePlatform;
import com.cream.helper.mapper.mock.MockRemoteUserMapper;
import com.cream.helper.obj.domain.vo.ServerVO;
import com.cream.helper.obj.entity.mock.MockRemoteUser;
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
    public String loginUser(String username, String password) throws Err {
        MockRemoteUser mockRemoteUser = mockRemoteUserMapper.getUser(username);
        if (mockRemoteUser == null) {
            throw new Err("未注册");
        } else {
            if (!password.equals(mockRemoteUser.getPassword())) {
                throw new Err("密码错误");
            }
            return "OK";
        }
    }

    @Override
    public void registerRemote(String username, String password) throws Err {
        try {
            MockRemoteUser mockRemoteUser = mockRemoteUserMapper.getUser(username);
            if (mockRemoteUser != null) {
                throw new Err("用户名已注册");
            }
            mockRemoteUser = new MockRemoteUser(username, password);
            mockRemoteUserMapper.insert(mockRemoteUser);
        } catch (Exception e) {
            throw new Err(e.getMessage());
        }
    }


    /**
     * 抓取可用的服务器列表
     */
    @Override
    public List<ServerVO> fetchServerList() {
        ServerVO dev = new ServerVO("研发服", "192.168.1.1", 6666, 1);
        ServerVO beta = new ServerVO("beta服", "192.168.2.2", 8888, 2);
        ServerVO trunk = new ServerVO("稳定服", "192.168.3.3", 6688, 3);
        ServerVO mockServer = new ServerVO("模拟服", GamePlatform.MockServer.ip, GamePlatform.MockServer.port, 1);
        return new ArrayList<>(Arrays.asList(dev, beta, trunk, mockServer));
    }

}
