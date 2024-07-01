package com.example.member.dto;

import com.example.member.entity.MemberEntity;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString
public class MemberDto {

    private Long id;
    private String memberEmail;
    private String memberName;
    private String memberPassword;

    public static MemberDto toMemberDTO(MemberEntity  memberEntity) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setMemberEmail(memberEntity.getMemberemail());
        memberDto.setMemberName(memberEntity.getMembername());
        memberDto.setMemberPassword(memberEntity.getMemberpassword());
        return memberDto;
    }


}
