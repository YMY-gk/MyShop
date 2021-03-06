package com.me.user.service.impl;

import com.me.user.pojo.UndoLog;
import com.me.user.mapper.UndoLogMapper;
import com.me.user.service.IUndoLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guokui
 * @since 2021-12-24
 */
@Service
public class UndoLogServiceImpl extends ServiceImpl<UndoLogMapper, UndoLog> implements IUndoLogService {

}
