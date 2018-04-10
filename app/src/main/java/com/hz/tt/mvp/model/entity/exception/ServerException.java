package com.hz.tt.mvp.model.entity.exception;

import com.hz.tt.R;
import com.hz.tt.util.UIUtils;

/**
 */
public class ServerException extends Exception {

    public ServerException(int errorCode) {
        this(UIUtils.getString(R.string.error_code) + errorCode);
    }

    public ServerException(String message) {
        super(message);
    }

}
