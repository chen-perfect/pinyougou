package com.pinyougou.shop.service;

import com.pinyougou.pojo.Seller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @program: pinyougou
 * @description: 用户认证服务类
 * @author: Mr.Chen
 * @create: 2018-07-30 11:08
 **/
public class UserDetailsServiceImpl implements UserDetailsService {
    /** 注入商家服务接口代理对象 */
    private SellerService sellerService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /** 根据商家的id查询商家 */
        Seller seller = sellerService.findOne(username);
        if (seller != null && "1".equals(seller.getStatus())) {
            /** 创建List集合封装角色或权限 */
            ArrayList<GrantedAuthority> grantedAuths = new ArrayList<>();
            /** 添加角色 */
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
            /** 返回用户信息对象 */
            return new User(username,seller.getPassword(), grantedAuths);
        }
        return null;
    }
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }
}
