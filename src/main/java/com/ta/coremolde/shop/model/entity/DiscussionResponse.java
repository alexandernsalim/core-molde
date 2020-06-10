package com.ta.coremolde.shop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discussion_response", schema = "public")
public class DiscussionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "message")
    private String message;

    @Column(name = "shop_id")
    private Integer shopId;

    @Column(name = "shop_user_id")
    private Integer shopUserId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "discussion_id", referencedColumnName = "id")
    private Discussion discussion;

}
