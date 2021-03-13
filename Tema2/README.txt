In implementarea temei am folosit scheletul, dar am sters interfata Intersection pentru ca mai mult ma incurca. 
Pentru a putea folosi restul scheletului mi-am pus toate variabilele de care aveam nevoie la diferitele cerinte in SimpleIntersection si am scris si cateva functii denumite sugestiv startX care pregateau resursele necesare pentru taskul X.
ReaderHandlerul pentru fiecare task seta variabilele necesare si apela eventual functia startX din SimpleIntersection.
Am comentat sau am omis unele sleepuri care nu erau importante ca sa grabesc checkerul local dar mai ales pe cel de pe vmchecker care am inteles ca avea timpi de asteptare foarte mari.
Am pus prin cod comentarii la liniile mai importante.
La taskul 1 fiecare masina anunta cand intra in intersectie, iar apoi dupa ce reusea sa parcurga intersectia anunta cand iese din intersectie.
La taskul 2 folosesc un semafor care lasa sa intre maxim nr de masini indicat in fisierul de intrare.
La taskul 3 folosesc un vector de semafoare care corespund fiecarui sens. Fiecare semafor da drumul cate unei masini.
La taskul 4 folosesc mai intai o bariera care asteapta toate masinile. Apoi folosesc o pereche de semafor cu bariera care se asigura ca trec exact nr de masini specificat.
La taskul 5 folosesc un semafor care are grija sa intre maxim nr de masini specificat.
La taskul 6 folosesc wait notify in felul urmator. Masinile cu prioritate updateaza mereu nr de masini cu prioritate din intersectie. Cand nu mai sunt masini cu prioritate in intersectie se apeleaza notify all. Masnilie fara prioritate care nu pot intra cand ajung se noteaza intr-o coada , iar cand primesc notify trec in ordine.
La taskul 7 folosesc un semafor in care pietonii sunt cei care dau release. Ei dau release la nr masini cand incep trecerea si cand termina trecerea. Observam ca masinile primesc culoarea verde la inceput, iar apoi primesc perechi de rosu cu verde.
La taskul 8 folosesc o combinatie de semafor cu bariera pentru a face sa treaca exact nr de masini indicat. Dupa ce trece cate o masina face release pe semaforul celuilalt sens.
La taskul 10 folosesc o bariera pentru a astepta toate masinile, apoi una dintre ele anunta ca trenul a trecut, iar restul pot continua drumul. Folosesc o coada pentru a tine minte ordinea in care trec masinilie si trebuie sa am grija si ca scrierea sa se faca bine.
