Croicia Rares - 323CC
# Tema POO - Catalog electronic

## Despre implementare

- Am implementat toate lucrurile impuse de tema, nu am avut timp sa fac mai mult
- GUI functional in majoritatea cazurilor, cel putin ce am testat :)
- Am adaugat in unele clase mai multe metode pentru a ma ajuta la pagini

## Despre fiecare clasa

### Catalog

- Asa cum zice in tema, clasa foloseste Singleton Design Pattern, lucru care
m-a ajutat sa pot sa nu initializez o instanta de catalog peste tot si sa o
folosesc pe aceeasi
- In catalog am stocat si un ScoreVisitor pentru a putea adauga toate notele de la
toti profesorii la un loc

### User si toti mostenitorii

- Despre interfete o sa vorbesc mai incolo
- Aici am facut exact ce s-a spus in cerinta
- Clasa Parent tine o referinta si la pagina parintelui respectiv

### Grade

- Getteri si setteri + implementeaza interfetele respective, nimic special

### Group

- In afara de getteri si setteri, avem functia `hasStudent` care verifica daca
un student exista in grupa.

### Course

- Am implementat toate metodele cerute de cerinta
- `studentExists(student)` Verifica daca exista un student la cursul respectiv
- `getAssistant(student)` Returneaza fie asistentul studentului la cursul respectiv, fie null
- `getPartialScore`/`getExamScore`/`getTotalScore(student)` returneaza nota studentului

## Despre fiecare interfata

### Observer

- Este implementata de clasa `Parent`, deoarece el e cel care asteapta notificare
pentru notele copilului respectiv
- Interfata `Subject` este implementata de clasa `Student`, deoarece eu am gandit ca 
el este cel care primeste notele si era usor sa ma folosesc de el pentru a informa
parintii

### Strategy

- Implementata de cele 3 clase `Best...`
- Nimic special fata de cere in cerinta

### Visitor

- Interfata `Element` este implementata de `Teacher` si `Assistant`, pentru ca ei
sunt cei care pun notele in catalog
- Interfata `Visitor` este implementata de `ScoreVisitor`

#### Clasa ScoreVisitor

- Contine tupla generica
- `teacherExists` verifica daca exista deja profesorul in cheile dictionarului
- `assistantExists` face la fel ca functia de mai sus, doar ca pentru asistent :)
- `getTeacherTuple` returneaza array-ul de tuple ca array de stringuri pentru
a ma ajuta la pagina profesorului
- `getAssistantTuple` face la fel ca functia de mai sus, doar ca pentru asistent :)
- `addExamScore` adauga score-ul in dictionarul profesorului
- `addPartialScore` face la fel ca functia de mai sus, doar ca pentru asistent :)
- `visit(teacher)` verifica daca exista deja nota in catalog si daca exista, doar o updateaza,
daca nu exista, o adauga. 
- `visit(asistent)` :)

## GUI

### Student Page

- Pagina de student contine toate cursurile la care este inrolat, iar la click pe
fiecare curs, ii va da informatiile necesare despre fiecare + notele pe care le are

### Teacher/Assistant Page

- Fiecare pagina arata atat cursurile la care preda profesorul/asistentul, cat si
toate notele pe care le au trecute "in carnetel", pentru a le trece in catalog
- Butonul de valideaza trece toate notele in catalog.

### Parent Page

- Pagina de parinte este o pagina care doar primeste notificari cand copilul primeste 
note

## Concluzii

- Tema a fost foarte faina, multe chestii invatate/aprofundate din aceasta tema, plus
- a fost interesant sa vad cum se folosesc design pattern-urile "pe viu"
- Functionalitatea a fost testata pe parcurs, a mers mereu pe testele pe care le dadeam
- Pentru a porni GUI-ul, se ruleaza clasa Test, avand metoda main