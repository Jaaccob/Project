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



INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (1, 'Jakub', 'Kozubek', 'Jaaccob', 'jakoz',
        'kozubekjacob@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (2, 'Wojciech', 'Witos', 'Rats', 'wojwi',
        'wojciechWitos@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (3, 'Jakub', 'Wasyluk', 'BriX', 'jakwa',
        'BriXb@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (4, 'Michał', 'Pączek', 'Poncho', 'micpa',
        'Ponchix@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (5, 'Pawel', 'Eskobar', 'Pablos', 'pawes',
        'Pablos@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (6, 'Akashima', 'Kazuto', 'Kirigaya', 'akaka',
        'KazutoKirigaya@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (7, 'Andżej', 'Wąsaty', 'Wexel', 'andwa',
        'Wąsaty_Wexel@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (8, 'Dominik', 'Bzik', '4everB', 'dombz',
        '4Bzikever@gmail.com');
INSERT INTO USERS(id, first_Name, last_Name, password, nick, email)
VALUES (9, 'Kamari', 'Due', 'Murderer', 'kamdu',
        'Due_To_Murder@gmail.com');


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