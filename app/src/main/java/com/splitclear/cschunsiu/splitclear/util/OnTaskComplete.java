package com.splitclear.cschunsiu.splitclear.util;

import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

public interface OnTaskComplete {
    void onOutput(List<Group> result);
}
