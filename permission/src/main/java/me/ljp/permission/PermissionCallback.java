package me.ljp.permission;

import java.io.Serializable;

/**
 * Created by pocketEos on 2017/5/10 0010.
 */

public interface PermissionCallback extends Serializable {
    void onClose();

    void onFinish();

    void onDeny(String permission, int position);

    void onGuarantee(String permission, int position);
}
