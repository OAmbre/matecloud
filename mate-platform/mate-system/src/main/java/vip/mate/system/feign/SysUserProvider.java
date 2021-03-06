package vip.mate.system.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.system.entity.SysUser;
import vip.mate.system.service.ISysUserService;

@Slf4j
@RestController
@AllArgsConstructor
public class SysUserProvider implements ISysUserProvider {

    private ISysUserService sysUserService;

    @Override
    @GetMapping("/provider/user-info-by-id")
    public Result<SysUser> userInfoById(Long userId) {
        SysUser sysUser = sysUserService.getById(userId);
        return Result.data(sysUser);
    }

    @Override
    @GetMapping("/provider/user-info")
    public Result<SysUser> loadUserByUserName(String userName) {
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().lambda().in(SysUser::getAccount, userName));
        log.info("feign调用：user:{}", sysUser);
        return Result.data(sysUser);
    }
}
