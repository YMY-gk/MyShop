package com.me.goods.service.impl;

import com.me.goods.pojo.TbAlbum;
import com.me.goods.mapper.TbAlbumMapper;
import com.me.goods.service.ITbAlbumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@Service("TbAlbumServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class TbAlbumServiceImpl extends ServiceImpl<TbAlbumMapper, TbAlbum> implements ITbAlbumService {

}
