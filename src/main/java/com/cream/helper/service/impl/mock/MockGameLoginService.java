package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.config.configuration.exception.CommonError;
import com.cream.helper.mapper.mock.MockRemoteUserMapper;
import com.cream.helper.obj.Ret;
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
    public String loginUser(String username, String password) throws CommonError {
        MockRemoteUser mockRemoteUser = mockRemoteUserMapper.getUser(username);
        if (mockRemoteUser == null) {
            throw new CommonError("未注册");
        } else {
            if (!password.equals(mockRemoteUser.getPassword())) {
                throw new CommonError("密码错误");
            }
            return "OK";
        }
    }

    @Override
    public void registerRemote(String username, String password) throws CommonError {
        try {
            MockRemoteUser mockRemoteUser = mockRemoteUserMapper.getUser(username);
            if (mockRemoteUser != null) {
                throw new CommonError("用户名已注册");
            }
            mockRemoteUser = new MockRemoteUser(username, password);
            mockRemoteUserMapper.insert(mockRemoteUser);
        } catch (Exception e) {
            throw new CommonError(e.getMessage());
        }
    }


    /**
     * 抓取可用的服务器列表
     */
    @Override
    public Ret<List<ServerVO>> fetchServerList() {
        ServerVO dev = new ServerVO("研发服", "192.168.1.1", 6666);
        ServerVO beta = new ServerVO("beta服", "192.168.2.2", 8888);
        ServerVO trunk = new ServerVO("稳定服", "192.168.3.3", 6688);
        List<ServerVO> serverList = new ArrayList<>(Arrays.asList(dev, beta, trunk));
        return Ret.ok(serverList);
    }

}
