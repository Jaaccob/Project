INSERT INTO TYPES(id, name)
VALUES (1, 'RPG');
INSERT INTO TYPES(id, name)
VALUES (2, 'Akcja');
INSERT INTO TYPES(id, name)
VALUES (3, 'Postapo');
INSERT INTO TYPES(id, name)
VALUES (4, 'Sandbox');
INSERT INTO TYPES(id, name)
VALUES (5, 'Zombie');
INSERT INTO TYPES(id, name)
VALUES (6, 'Sience fiction');
INSERT INTO TYPES(id, name)
VALUES (7, 'Strategiczne');
INSERT INTO TYPES(id, name)
VALUES (8, 'RTS');
INSERT INTO TYPES(id, name)
VALUES (9, 'Turowe');
INSERT INTO TYPES(id, name)
VALUES (10, 'Wyścigowe');
INSERT INTO TYPES(id, name)
VALUES (11, 'TTP');
INSERT INTO TYPES(id, name)
VALUES (12, 'Fantasy');
INSERT INTO TYPES(id, name)
VALUES (13, 'Kooperacja');
INSERT INTO TYPES(id, name)
VALUES (14, 'FPP');
INSERT INTO TYPES(id, name)
VALUES (15, 'PostApokalipsa');

INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (1,
        'Elden ring',
        'https://image.api.playstation.com/vulcan/ap/rnd/202107/1612/Y5RHNmzAtc6sRYwZlYiKHAxN.png',
        'Elden Ring to gra RPG akcji opracowana przez FromSoftware i wydana przez Bandai Namco Entertainment. Gra ' ||
        'została wyreżyserowana przez Hidetakę Miyazakiego i stworzona we współpracy z powieściopisarzem fantasy ' ||
        'Georgem R.R. Martinem, który dostarczył materiał na scenerię gry',
        'FromSoftware');

INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (2,
        'Dying Light 2',
        'https://image.api.playstation.com/vulcan/img/rnd/202106/2908/7aJhOMuJALdBPqZHVy3CgJsg.png',
        'Kontynuacja gry akcji FPP z otwartym światem autorstwa polskiego studia Techland, w której eksplorujemy ' ||
        'miasto zniszczone przez wirus zmieniający ludzi w zombie. Dying Light 2 odróżnia się od poprzednika większym ' ||
        'naciskiem na elementy RPG, z wyborami moralnymi na czele.',
        'Techland');

INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (3,
        'Horizon Forbidden West',
        'https://image.api.playstation.com/vulcan/ap/rnd/202107/3100/LcBzIX6UFHmJIC04EDFKz6IK.png',
        'W Horizon Forbidden West trafiamy na zachód postapokaliptycznej Ameryki, gdzie Aloy szuka sposobu na uporanie ' ||
        'się z nowym zagrożeniem dla resztek ludzkości. To druga część serii przygodowych gier akcji z otwartym światem ' ||
        'i elementami RPG od studia Guerrilla Games.',
        'Sony');

INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (4,
        'Age of Empires IV',
        'https://www.hrkgame.com/media/games/.thumbnails/AgeofEmpiresIVDeluxe_cover.jpg/AgeofEmpiresIVDeluxe_cover-460x215.jpg',
        'Age of Empires 4 to czwarta pełnoprawna odsłona słynnego cyklu strategii czasu rzeczywistego, osadzona w ' ||
        'średniowieczu. Za jej stworzenie odpowiedzialna jest ekipa Relic Entertainment, znana z takich serii jak ' ||
        'Company of Heroes czy Warhammer 40,000: Dawn of War.',
        'Microsoft');

INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (5,
        'Total War: Warhammer III',
        'https://assets.xboxservices.com/assets/4b/cf/4bcf2112-6880-4caf-8e13-8173ad811a2b.jpg?n=Warhammer-III_Poster-Image-1084_1920x1080.jpg',
        'Trzecia i ostatnia część podcyklu serii Total War na licencji Warhammera. Total War: Warhammer 3 wprowadza ' ||
        'm.in. frakcje Kislevu i Kataju, które biorą udział w starciach o jeszcze większym rozmachu niż dotychczas.',
        'SEGA');
INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (6,
        'Age of Empires III: Definitive Edition PC',
        'https://image.ceneostatic.pl/data/products/98075731/i-age-of-empires-iii-definitive-edition-digital.jpg',
        'Trzecia część odświeżona i poprawiona wersja Age of Empires III. Age of Empires III: Definitive Edition ' ||
        'jest dziełem studia Forgotten Empires, odpowiedzialnego za remastery pierwszej i drugiej części serii. Zmiany ' ||
        'objęły przede wszystkim oprawę graficzną i interfejs.',
        'Microsoft');
INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (7,
        'Stunt GP ',
        'https://cdn.gracza.pl/galeria/gry13/grupy/1578.jpg',
        'Trzecia i ostatnia część Można nimi skakać, jeździć po rampach, spiralach i innych atrakcjach znajdujących ' ||
        'się na przygotowanych specjalnie w tym celu torach.',
        'Team17');
INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (8,
        'Metin2',
        'https://bananki-15199.kxcdn.com/photos/bananapedia/e18ea27bc84a4ae815a005cb18fb5e73.jpg',
        ' gra komputerowa z gatunku MMORPG, w której gracze rywalizują w trzech odrębnych królestwach. Wyprodukowana ' ||
        'przez Ymir Entertainment i wydana w marcu 2005 roku. Poza Koreą i Chinami wydawana jest przez Gameforge w ' ||
        'Europie i Stanach Zjednoczonych. Polska wersja gry została wydana 17 lipca 2007 roku',
        'Ymir Entertainment');
INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (9,
        'Oxygen Not Included',
        'https://i.ytimg.com/vi/wcLayGm_pM4/maxresdefault.jpg',
        'Osadzona w realiach science fiction, dwuwymiarowa gra symulacyjna kanadyjskiego studia Klei Entertainment, ' ||
        'w której zajmujemy się rozwijaniem kosmicznej kolonii na bliżej nieokreślonej planecie. ',
        'Klei Entertainment');
INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (10,
        'Dyson Sphere Program',
        'https://cdn.gracza.pl/galeria/gry13/534518515.jpg',
        'Dyson Sphere Program to kosmiczna strategia ekonomiczna, w której zarządzamy i rozbudowujemy bazę,'||
        ' dbamy o ekonomię, inne zasoby i automatyzację procesów oraz eksplorujemy wszechświat w celu stworzenia prężnie działającego imperium.',
        ' Youthcat Studio');
INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (11,
        'Galactic Civilizations IV',
        'https://cdn.gracza.pl/galeria/gry13/629203015.jpg',
        'Czwarta odsłona popularnych gier strategicznych, opracowana przez studio Stardock.' ||
        'Wciel się w przywódcę kosmicznej cywilizacji i odkryj na nowo rozbudowany i udoskonalony świat Galactic Civilizations IV.',
        ' Stardock Corporation');
INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (12,
        'Romans: Age of Caesar',
        'https://cdn.gracza.pl/galeria/gry13/199460250.jpg',
        'Duchowy spadkobierca gry Stronghold Kingdoms - strategia MMO od studia FireFly (ojców cyklu Twierdza),' ||
        'w której gracze wcielają się w zarządców antycznych rzymskich miast. ',
        'Firefly Studios');
INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (13,
        'Distant Worlds 2',
        'https://cdn.gracza.pl/galeria/gry13/grupy/1211795281.jpg',
        'Distant Worlds 2 jest kontynuacją ciepło przyjętej przez graczy produkcji RTS z podgatunku 4X. '||
        'Gra została stworzona przez studio Code Force, czyli twórców pierwszej części. Tytuł odznacza się dużą skalą świata oraz możliwością dopasowania rozgrywki do własnych preferencji.',
        'Code Force Limited');
INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (14,
        'Songs of Conquest',
        'https://cdn.gracza.pl/galeria/gry13/grupy/14253921.jpg',
        'Turowa strategia fantasy wzbogacona elementami RPG, nawiązująca do pierwszych odsłon serii Heroes of Might & Magic. Gracz musi zarówno zarządzać królestwem,'||
        'jak i dowodzić drużyną podczas eksplorowania królestwa i wypełniania zadań. Do tego dochodzą taktyczne potyczki.',
        'Lavapotion');
INSERT INTO GAME(id, title, imageURL, description, author)
VALUES (15,
        'Hearthstone',
        'https://cdn.gracza.pl/galeria/gry13/grupy/13826.jpg',
        'Karciana gra logiczna osadzona w fantastycznym świecie Azeroth, znanym z cyklu Warcraft. Tytuł wyprodukowany został przez firmę Blizzard Entertainment, '||
        'a źródłem inspiracji dla deweloperów stała się m.in. kultowa, kolekcjonerska gra karciana Magic: The Gathering.',
        'Blizzard Entertainment');

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (1, 1);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (1, 11);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (1, 12);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (1, 13);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (2, 2);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (2, 14);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (2, 5);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (2, 15);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (3, 2);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (3, 11);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (3, 6);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (3, 15);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (4, 7);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (4, 8);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (5, 7);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (5, 12);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (5, 8);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (5, 10);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (6, 7);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (6, 8);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (6, 13);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (7, 10);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (8, 1);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (9, 4);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (9, 7);

INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (10, 1);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (10, 5);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (11, 8);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (11, 10);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (12, 2);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (12, 5);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (12, 3);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (13, 9);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (13, 2);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (14, 1);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (14, 8);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (15, 9);
INSERT INTO CONNECT_GAME_TYPE(ID_GAME, ID_TYPE)
VALUES (15, 4);

INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (1, 'Jakub', 'Kozubek', '$2a$10$h68AcQfPAN4VVibeet/VDOT/ibHPT.3mS3Qn/M9ni/sNXbEXQdTty', 'jakoz',
        'kozubekjacob@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (2, 'Wojciech', 'Witos', '$2a$10$thcAEXsBpIXtjyTP.icz/ueEOgkYQmEqyTkb7WaxVieAhJpGdqYke', 'wojwi',
        'wojciechWitos@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (3, 'Jakub', 'Wasyluk', '$2a$10$vAzqom5/TfIWIgtBw8i9teQTYS3iP74mbbzxMQ1cgrthkj/GuuSHK', 'jakwa',
        'BriXb@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (4, 'Michał', 'Pączek', '$2a$10$g18mqRnxptyrXd7Gem2OLOFL28Q2NlHNEQUXTCjM6zdCSUO38mhT6', 'micpa',
        'Ponchix@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (5, 'Pawel', 'Eskobar', '$2a$10$PeD/1MPxqHzcCe/np9i3HOeI9.KujdAU6z8e4cLCUYKEmqx3ixaGK', 'pawes',
        'Pablos@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (6, 'Akashima', 'Kazuto', '$2a$10$S/I1pf12EIL5JEyTCq5uPu.2t7RBkkbVCe0Xpwx5bKz64k6miu4Fy', 'akaka',
        'KazutoKirigaya@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (7, 'Andżej', 'Wąsaty', '$2a$10$FSwzTKJ.cdfj46p02ki0qOGTNUfeOGXagA1zGzVVXGFa1xvSTBct6	', 'andwa',
        'Wąsaty_Wexel@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (8, 'Dominik', 'Bzik', '$2a$10$jcUsppBcKFGhxx0qJ/FA0u2GiutGcsx8sUCf0CCNhEmb6EYEqrxOe', 'dombz',
        '4Bzikever@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (9, 'Kamari', 'Due', '$2a$10$68XINjWZRpAEF95pi1sqZ.14JY5CWKnV64fXmnRdalDwL5Hv8YiMe', 'kamdu',
        'Due_To_Murder@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (10, 'Conqueror', 'WR', '$2a$10$z8RoVCUO9c29iqWuJ44LIOoapUw71LFmjBYJpgeKb9gkT5JJKLSl6', 'xasw',
        'Walix15@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (11, 'Samari', 'Amalejkum', '$2a$10$5A.G2Q9W/eBdZIr/s5trCuVHIb76xLu.D9F5DDEBgztGl.Mmr9X5m', 'WOrldDestroyer',
        'Krawczykonus@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (12, 'Paweł', 'Bisok', '$2a$10$ZDZswm1tVHZG5xTTlcOXg.Q8kQi7kdoWYL11I3TXfYhzL//Z39IR2', 'Woxis',
        'PabloEnrico@gmail.com');

INSERT INTO FOLLOWED_GAMES(id_User, followed_Id_Game)
VALUES (1, 1);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (1, 2);
INSERT INTO FOLLOWED_GAMES(id_User, followed_Id_Game)
VALUES (2, 9);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (2, 4);
INSERT INTO FOLLOWED_GAMES(id_User, followed_Id_Game)
VALUES (3, 5);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (3, 8);
INSERT INTO FOLLOWED_GAMES(id_User, followed_Id_Game)
VALUES (4, 5);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (4, 8);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (4, 2);
INSERT INTO FOLLOWED_GAMES(id_User, followed_Id_Game)
VALUES (5, 4);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (5, 2);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (5, 3);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (5, 1);
INSERT INTO FOLLOWED_GAMES(id_User, followed_Id_Game)
VALUES (6, 4);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (6, 2);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (6, 8);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (7, 5);
INSERT INTO FOLLOWED_GAMES(id_User, followed_Id_Game)
VALUES (7, 5);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (8, 4);
INSERT INTO FOLLOWED_GAMES(id_User, followed_Id_Game)
VALUES (9, 4);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (9, 3);
INSERT INTO FOLLOWED_GAMES(id_User, followed_Id_Game)
VALUES (9, 1);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (9, 7);

INSERT INTO FOLLOWED_GAMES(id_User, followed_Id_Game)
VALUES (10, 1);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (10, 4);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (11, 12);
INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
VALUES (11, 12);
-- INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
-- VALUES (12, 13);
-- INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
-- VALUES (12, 14);
-- INSERT INTO FOLLOWED_USERS(id_User, id_Followed_User)
-- VALUES (12, 15);


INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (1, 1, 1, 5,
        '2022-01-23T13:12:58.236956900', 'Gra całkiem ciekawa, podoba mi sie');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (2, 1, 2, 1,
        '2022-03-12T11:55:58.236956900', 'Zacina się, laguje, nie polecam.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (3, 1, 3, 3,
        '2022-02-25T13:45:58.236956900', 'Dość ciekawa, ale niezbyt porywająca.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (4, 1, 4, 1,
        '2021-01-23T20:15:25.236956900', 'Nie kupuj.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (5, 1, 5, 2,
        '2021-05-24T14:13:27.236956900', 'Ładna grafika, nic poza tym.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (6, 2, 1, 5,
        '2020-01-19T15:12:58.236956900', 'Polecam każdemu, ciekawa fabuła.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (7, 2, 2, 5,
        '2021-02-26T11:12:23.236956900', 'Kupuj bez pytania, kolorowy świat i ciekawy dobór postaci.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (8, 2, 3, 5,
        '2021-01-11T13:17:54.236956900', 'Porywająca fabuła, ciekawy gameplay.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (9, 2, 4, 1,
        '2021-08-22T11:56:45.236956900', 'Poza brzydką grafiką, bardzo słaby performance.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (10, 4, 5, 4,
        '2020-11-23T18:12:58.236956900', 'Słabe charaktery postaci, poza tym polecam.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (11, 5, 7, 5,
        '2022-01-23T13:12:58.236956900', 'Porywająca gierka, nieprzespałem nocy');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (12, 3, 8, 1,
        '2020-12-23T19:56:51.236956900', 'Musieliby mi jeszcze dopłacić, żebym do tego wrócił. REFOUND');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (13, 7, 9, 3,
        '2022-01-30T19:14:22.236956900', 'Nic specjalnego.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (14, 9, 8, 1,
        '2021-01-28T13:12:47.236956900', 'Dno dna.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (15, 7, 6, 2,
        '2020-02-11T04:32:22.236956900', 'Muzyka ratuje to coś co nazywane jest grą.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (16, 9, 5, 5,
        '2021-03-23T13:12:58.236956900', 'Mocno polecam, porywająca.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (17, 4, 6, 5,
        '2020-07-23T15:13:27.236956900', 'Ta gra to jakiś dziwny twór.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (18, 3, 1, 5,
        '2021-01-23T21:37:24.236956900', 'Nieprzespane noce...');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (19, 1, 2, 1,
        '2020-11-19T15:12:58.236956900', 'Śnią mi się przez nią koszmary.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (20, 8, 3, 4,
        '2020-01-23T13:45:23.236956900', 'Gdyby tylko fabuła była trochę ciekawsza dałbym 5');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (21, 12, 11, 5,
        '2021-03-23T13:45:23.236956900', 'Całkiem koks gierka.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (22, 10, 12, 5,
        '2022-04-23T13:45:23.236956900', 'Wziuuum, 3ci dzień bez snu, gramy dalej.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (23, 11, 15, 3,
        '2021-03-23T13:45:23.236956900', 'Taka mehhhhh');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (24, 4, 14, 2,
        '2021-02-23T13:45:23.236956900', 'Nie przepadam, bardzo średnia :/');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (25, 12, 13, 2,
        '2022-04-23T13:45:23.236956900', 'Dobrze, że chociaż pieniądze zwrócili.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (26, 5, 10, 4,
        '2021-11-23T13:45:23.236956900', 'Zależy od dnia i humoru, dzisiaj jest 4');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (27, 3, 13, 1,
        '2022-03-23T13:45:23.236956900', 'Dno i wodorosty.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (28, 11, 7, 5,
        '2021-12-23T13:12:58.236956900', 'Polecam.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (29, 10, 9, 5,
        '2022-01-30T19:14:22.236956900', 'Pograne.');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (30, 5, 10, 4,
        '2021-11-23T13:45:23.236956900', 'Całkiem koks. ');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (31, 10, 11, 3,
        '2021-03-23T13:45:23.236956900', 'Średniawa');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (32, 3, 12, 1,
        '2021-12-23T13:45:23.236956900', 'Nie polecam');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (33, 9, 14, 2,
        '2022-03-23T13:45:23.236956900', 'Bardzo słabo jak na taki budżet...');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (34, 1, 14, 3,
        '2021-12-23T13:45:23.236956900', 'Nie wiem co powiedzieć, nie porwało...');
INSERT INTO REVIEW(id_Review, id_User, id_Game, mark, date, description)
VALUES (35, 11, 15, 5,
        '2021-03-23T13:45:23.236956900', 'Polecanko');
