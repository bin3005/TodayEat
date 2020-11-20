package resource;

import java.awt.Component;
import java.util.HashMap;

import member.model.vo.Member;

public interface R {
	HashMap<String,Component> component = new HashMap<String, Component>();
	HashMap<String,Member> loginedMember = new HashMap<String, Member>();
	
	
}
