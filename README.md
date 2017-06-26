Сервис принимает на адрес http://localhost:8080/pizza post запросы содержащие JSON в теле.

JSON имеет вид: {"SIZE":"VALUE", "ingredients":["VALUE", "VALUE"]}

Примеры JSON:

Пицца готова: {"size":"LARGE", "ingredients":["SHRIMPS", "BEEF"]}

Пицца не готова: {"size":"LARGE", "ingredients":["PORK", "ONION"]}

Список размеров:
SMALL, MEDIUM, LARGE, EXTRA_LARGE

Список ингредиентов:
MOZZARELLA, PAPRIKA, CHICKEN, BEEF, PORK, ONION, GARLIC, OLIVES, PINEAPPLE, SHRIMPS, SALMON


На данный момент есть баг, что сервис возвращает ингридиенты в случайном порядке, поэтому часть тестов проваливается.
