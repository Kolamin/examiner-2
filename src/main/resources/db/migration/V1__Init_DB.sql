create table correct_gaz_answer
(
    id              bigserial not null,
    correct_answer varchar(4000),
    primary key (id)
);

create table correct_heat_answer
(
    id              bigserial not null,
    correct_answer varchar(4000),
    primary key (id)
);

create table gaz_questions
(
    id      bigserial not null,
    answer varchar(255),
    name   varchar(4000),
    primary key (id)
);

create table gaz_questions_test_options
(
    gaz_questions_id int8 not null,
    test_options     varchar(4000)
);

create table heat_questions
(
    id      bigserial not null,
    answer varchar(255),
    name   varchar(4000),
    primary key (id)
);

create table heat_question_test_options
(
    heat_question_id int8 not null,
    test_options     varchar(4000)
);

alter table if exists gaz_questions_test_options
    add constraint gaz_questions_fk foreign key (gaz_questions_id) references gaz_questions;
alter table if exists heat_question_test_options
    add constraint heat_question_fk foreign key (heat_question_id) references heat_questions;