package com.example.member.entity;

import com.example.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberemail;

    @Column
    private String membername;

    @Column
    private String memberpassword;

    public static MemberEntity toMemberEntity(MemberDto memberDto) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberemail(memberDto.getMemberEmail());
        memberEntity.setMembername(memberDto.getMemberName());
        memberEntity.setMemberpassword(memberDto.getMemberPassword());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberDTO(MemberDto  memberDto) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDto.getId());
        memberEntity.setMemberemail(memberDto.getMemberEmail());
        memberEntity.setMembername(memberDto.getMemberName());
        memberEntity.setMemberpassword(memberDto.getMemberPassword());
        return memberEntity;
    }



}
