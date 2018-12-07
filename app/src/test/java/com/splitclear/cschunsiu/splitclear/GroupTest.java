package com.splitclear.cschunsiu.splitclear;

import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

public class GroupTest {
    Group group = new Group("testGroup");
    @Mock
    private List<Member> testMemberList;

    @Before
    public void init(){
        group.setMemberList(testMemberList);
    }

    @Test
    public void testConstructor(){
        Group copy = new Group(group.getName());
        assertSame(copy.getMemberList(), group.getMemberList());
    }
}
