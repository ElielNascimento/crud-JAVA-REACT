
    create table tbl_filme (
       id int8 generated by default as identity,
        categoria varchar(255),
        nome varchar(255),
        primary key (id)
    );

    alter table if exists tbl_filme 
       add constraint UK_m7o25x3hcol7qx9k3vf3pa05n unique (nome);
