package com.alibou.spring_security.modules.user.helpers;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder // chua hieu cai nay la gì
@Getter
@Setter
public class ChangePasswordRequest {
    public String currentPassword;
    public String newPassword;
    public String confirmationPassword;
}
