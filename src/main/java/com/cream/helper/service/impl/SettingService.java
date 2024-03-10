package com.cream.helper.service.impl;

import com.cream.helper.config.configuration.exception.RunErr;
import com.cream.helper.core.net.common.MsgTemplatePool;
import com.cream.helper.core.net.common.constant.MsgType;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.mapper.AccountSetupMapper;
import com.cream.helper.obj.domain.dto.account.ModifyFilterMsgDTO;
import com.cream.helper.obj.domain.dto.account.SetDefaultServerDTO;
import com.cream.helper.obj.domain.dto.msg.MsgFilterSettingDTO;
import com.cream.helper.obj.domain.dto.msg.UpdateFilterSettingDTO;
import com.cream.helper.obj.domain.vo.account.setting.FilterMsgVO;
import com.cream.helper.obj.domain.vo.account.setting.MsgFilterSettingVO;
import com.cream.helper.obj.entity.account.AccountSetup;
import com.cream.helper.service.constant.FilterMsgSettingType;
import com.cream.helper.utils.NullUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class SettingService {

    private final AccountSetupMapper accountSetupMapper;
    private final MsgTemplatePool msgTemplatePool;

    private final Set<Integer> allDefaultFilterMsg;

    @Autowired
    public SettingService(AccountSetupMapper accountSetupMapper,
                          MsgTemplatePool msgTemplatePool) {
        this.accountSetupMapper = accountSetupMapper;
        this.msgTemplatePool = msgTemplatePool;
        this.allDefaultFilterMsg = initDefaultFilterMsg();
    }

    private Set<Integer> initDefaultFilterMsg() {
        Set<Integer> res = new HashSet<>();
        // 简单写死
        res.add(1);
        res.add(99);
        res.add(100);
        res.add(101);
        return Collections.unmodifiableSet(res);
    }

    public String getDefaultServer(long accountId) {
        return accountSetupMapper.getDefaultMapper(accountId);
    }

    public void setDefaultServer(SetDefaultServerDTO setDefaultServerDTO) {
        long accountId = setDefaultServerDTO.getAccountId();
        if (accountId == 0) {
            throw new RunErr("系统异常：检测到未登录~");
        }
        AccountSetup accountSetup = accountSetupMapper.selectById(accountId);
        if (accountSetup == null) {
            accountSetup = new AccountSetup(accountId);
            accountSetup.setDefaultServer(setDefaultServerDTO.getDefaultServer());
            accountSetupMapper.insert(accountSetup);
        } else {
            accountSetupMapper.updateDefaultServer(accountId, setDefaultServerDTO.getDefaultServer());
        }
    }

    public MsgFilterSettingVO getDefaultFilterMsg(long accountId) {
        // 默认过滤取消的id
        return getMsgFilterSettingVO(accountId, FilterMsgSettingType.Default);
    }

    private MsgFilterSettingVO getMsgFilterSettingVO(long accountId, FilterMsgSettingType settingType) {
        AccountSetup accountSetup = getAccountSetup(accountId);
        if (accountSetup == null) {
            return new MsgFilterSettingVO();
        }
        Set<Integer> filterMsgId;
        if (settingType == FilterMsgSettingType.Default) {
            filterMsgId = this.allDefaultFilterMsg;
        } else {
            filterMsgId = accountSetup.getCustomFilterMsgId();
        }
        Set<Integer> cancelFilterMsgId = accountSetup.getDefaultFilterCancelMsgId();
        MsgFilterSettingVO msgFilterSettingVO = new MsgFilterSettingVO();
        for (Integer msgId : filterMsgId) {
            Message<?> msgTemplate = msgTemplatePool.getMsgTemplate(msgId);
            if (msgTemplate == null) {
                continue;
            }
            boolean filter = true;
            if (settingType == FilterMsgSettingType.Default) {
                filter = !cancelFilterMsgId.contains(msgId);
            }
            FilterMsgVO filterMsgVO = new FilterMsgVO(msgId, msgTemplate.getName(), filter);
            if (msgTemplate.getMsgMeta().msgType == MsgType.Req) {
                msgFilterSettingVO.addReqFilterMsg(filterMsgVO);
            } else if (msgTemplate.getMsgMeta().msgType == MsgType.Res) {
                msgFilterSettingVO.addResFilterMsg(filterMsgVO);
            }
        }
        return msgFilterSettingVO;
    }


    public MsgFilterSettingVO getCustomerFilterMsg(long accountId) {
        return getMsgFilterSettingVO(accountId, FilterMsgSettingType.Custom);
    }

    public void modifyDefaultFilterMsg(ModifyFilterMsgDTO modifyDTO) {
        AccountSetup accountSetup = getAccountSetup(modifyDTO.getAccountId());
        if (accountSetup == null) {
            return;
        }
        List<Integer> cancelFilterMsgId = modifyDTO.getModifyMsgId();
        if (NullUtil.isEmpty(cancelFilterMsgId)) {
            accountSetup.setDefaultFilterCancelMsgId(Collections.emptySet());
        } else {
            accountSetup.setDefaultFilterCancelMsgId(new HashSet<>(cancelFilterMsgId));
        }
        accountSetupMapper.updateById(accountSetup);
    }

    public void modifyCustomFilterMsg(ModifyFilterMsgDTO modifyDto) {
        AccountSetup accountSetup = getAccountSetup(modifyDto.getAccountId());
        if (accountSetup == null) {
            return;
        }
        List<Integer> modifyMsgId = modifyDto.getModifyMsgId();
        Set<Integer> customFilterMsgId = modifyMsgId == null ? Collections.emptySet() : new HashSet<>(modifyMsgId);
        accountSetup.setCustomFilterMsgId(customFilterMsgId);
        accountSetupMapper.updateById(accountSetup);
    }

    private AccountSetup getAccountSetup(long accountId) {
        AccountSetup accountSetup = accountSetupMapper.selectById(accountId);
        if (accountSetup == null) {
            log.error("获取账户设置为空，accountId:{}", accountId);
            return null;
        }
        return accountSetup;
    }

    public MsgFilterSettingDTO changeMsgFilterSetting(UpdateFilterSettingDTO updateDTO) {
        long accountId = updateDTO.getAccountId();
        if (accountId == 0) {
            throw new RunErr("未登录");
        }
        AccountSetup accountSetup = accountSetupMapper.selectById(accountId);
        if (accountSetup == null) {
            throw new RunErr("账户为空");
        }
        FilterMsgVO filterMsgVO = updateDTO.getFilterMsgVO();
        if (filterMsgVO != null) {
            // todo 目前只做默认过滤的取消，后续再细化
            int msgId = filterMsgVO.getMsgId();
            boolean filter = filterMsgVO.isFilter();
            Set<Integer> defaultFilterCancelMsgId = accountSetup.getDefaultFilterCancelMsgId();
            if (filter) {
                defaultFilterCancelMsgId.remove(msgId);
            } else {
                defaultFilterCancelMsgId.add(msgId);
            }
            accountSetup.setDefaultFilterCancelMsgId(defaultFilterCancelMsgId);
            accountSetupMapper.updateById(accountSetup);
        }
        boolean resetDefaultFilter = updateDTO.isResetDefaultFilter();
        if (resetDefaultFilter) {
            accountSetup.setDefaultFilterCancelMsgId(Collections.emptySet());
            accountSetupMapper.updateById(accountSetup);
        }
        boolean clearCustomFilterMsg = updateDTO.isClearCustomFilter();
        if (clearCustomFilterMsg) {
            accountSetup.setCustomFilterMsgId(Collections.emptySet());
            accountSetupMapper.updateById(accountSetup);
        }
        MsgFilterSettingVO defaultFilterMsg = getDefaultFilterMsg(accountId);
        MsgFilterSettingVO customerFilterMsg = getCustomerFilterMsg(accountId);
        return new MsgFilterSettingDTO(defaultFilterMsg, customerFilterMsg);
    }
}
