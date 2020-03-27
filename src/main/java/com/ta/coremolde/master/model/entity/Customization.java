package com.ta.coremolde.master.model.entity;

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
@Table(name = "customization", schema = "public")
public class Customization {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "app_logo")
    private String appLogo;

    @Column(name = "app_background")
    private String appBackground;

    @Column(name = "app_font_color")
    private String appFontColor;

    @Column(name = "prod_layout")
    private String prodLayout;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

}
