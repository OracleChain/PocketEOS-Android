package me.ljp.permission;

import java.io.Serializable;

/**
 * Created by pocketEos on 2017/5/10 0010.
 */

public class PermissionItem implements Serializable {
    public String PermissionName;
    public String Permission;
    public int PermissionIconRes;

    public PermissionItem(String permission, String permissionName, int permissionIconRes) {
        Permission = permission;
        PermissionName = permissionName;
        PermissionIconRes = permissionIconRes;
    }

    public PermissionItem(String permission) {
        Permission = permission;
    }
}
