INSERT INTO question (question_text, answer, author) VALUES
    ('Чем ArrayList отличается от LinkedList?', 'ArrayList быстрее для случайного доступа, LinkedList быстрее для вставки/удаления в середине.', 'Wersatil'),
    ('В чем основное различием между HashMap, TreeMap и ConcurrentHashMap?', 'HashMap не сортирует ключи, TreeMap хранит ключи в отсортированном порядке, ConcurrentHashMap потокобезопасен.', 'Wersatil'),
    ('Что такое loadFactor в HashMap?', 'Порог заполнения HashMap, при превышении которого выполняется расширение массива.', 'Wersatil'),
    ('Какой complexity у get() и put() в HashMap?', 'Средняя сложность O(1), в худшем случае O(n) при больших коллизиях.', 'Wersatil'),
    ('Могут ли ключи HashMap быть null?', 'Да, один ключ может быть null.', 'Wersatil'),
    ('Что такое fail-fast iterator и какое отношение он имеет к HashMap?', 'Fail-fast iterator — это итератор, который быстро обнаруживает конкурентное изменение коллекции и немедленно выбрасывает исключение, вместо того чтобы продолжать работу с неконсистентными данными. В Java это обычно ConcurrentModificationException. Иттераторы HashMap - fail-fast.', 'Wersatil'),
    ('Чем грозит следующее выражение: Map.of("a", null);?', 'Если написать так, а не обернуть все это в HashMap, то будет NullPointerException.', 'Wersatil'),
    ('ConcurrentHashMap поддерживает null?', 'Нет, ключи и значения не могут быть null.', 'Wersatil'),
    ('Какие из следующих реализаций Map могут хранить null в качестве ключа или значения: HashMap, LinkedHashMap, ConcurrentHashMap?', 'HashMap и LinkedHashMap могут хранить только 1 ключ null и любое количество значений null. ConcurrentHashMap не может вообще хранить null ни в ключах, ни в значениях.', 'Wersatil'),
    ('Что работает быстрее: HashMap или TreeMap и благодаря чему?', 'HashMap имеет O(1) доступ к элементам, TreeMap O(log n) из-за того, что у HashMap прямой доступ по hashcode, а у Treemap красно-черное дерево поиска.', 'Wersatil'),
    ('Что такое threshold в HashMap?', 'Количество элементов, после которого происходит перераспределение (rehash).', 'Wersatil'),
    ('Что является родителем и наследниками интерфейса Collection?', 'Iterable. List, Set и Queue.', 'Wersatil');