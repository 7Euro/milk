package com.leo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leo.common.utils.StatusUtil;
import com.leo.common.result.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/21 16:22
 **/
@Data
@Entity
@Table(name = "sys_user")
@ToString(exclude = {"dept", "roles"})
@EqualsAndHashCode(exclude = {"dept", "roles"})
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update sys_user" + StatusUtil.SLICE_DELETE)
@Where(clause = StatusUtil.NOT_DELETE)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String salt;
    private String nickname;
    private String picture;
    private Byte sex;
    private String phone;
    private String email;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    private String remark;
    private Byte status = StatusEnum.OK.getCode();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    @JsonIgnore
    private Dept dept;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<Role> roles = new HashSet<>(0);
}
