# 2D-Симуляция Жизни

Это пошаговая симуляция 2D-мира, населённого травоядными и хищниками. В этом мире также есть ресурсы в виде травы, которой питаются травоядные. Кроме того, есть статичные объекты, которые не могут взаимодействовать с другими элементами мира (деревья и камни).

---

## Особенности

- **Мир** представлен в виде матрицы `NxM`. Каждое существо или объект занимает одну клетку. Нахождение нескольких объектов/существ в одной клетке недопустимо.
- **Существа**:
  - Испытывают голод, который уменьшает их здоровье.
  - Двигаются к своей цели.
  - Поедают траву / добычу, если находятся рядом с ней.
- **Хищники**:
  - Атакуют свою цель, пока у нее есть здоровье.
  - После убийства цели могут её съесть.
- **Создание существ** происходит в начале симуляции.
- Пользователь может:
  - **Останавливать симуляцию**.
  - **Выходить из симуляции**.
  - **Возобновлять симуляцию**.

---

## Настройки

Настройки симуляции задаются через конфигурационный файл или параметры в коде. Вы можете настроить:

- Размеры мира (`NxM`).
- Количество травы, камней, деревьев, хищников и травоядных.
- А также характеристики животных

Пример конфигурации:

```json
{
	"mapSize": {
		"mapHeight": 10,
		"mapWidth": 10
	},
	"balance": {
		"herbivore": 0.1,
		"predator": 0.05,
		"tree": 0.05,
		"rock": 0.05,
		"grass": 0.1
	},
	"entitiesSettings": {
		"herbivore": {
			"maxHealthPoints": 100,
			"maxHungry": 80,
			"moveSpeed": 2
		},
		"predator": {
			"maxHealthPoints": 120,
			"maxHungry": 100,
			"moveSpeed": 3
		},
		"grass": {
			"maxHealthPoints": 10
		}
	}
}

```
---

## Структура Проекта
```bash
.
├── Simulation.iml
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   │       └── Game
│   │           ├── Entities
│   │           │   ├── Coordinates.java
│   │           │   ├── Dynamic
│   │           │   │   ├── Creature.java
│   │           │   │   ├── Herbivore.java
│   │           │   │   └── Predator.java
│   │           │   ├── EntitiesRepresentation.java
│   │           │   ├── Entity.java
│   │           │   ├── Health.java
│   │           │   ├── Hungry.java
│   │           │   ├── LiveNature.java
│   │           │   ├── Static
│   │           │   │   ├── Grass.java
│   │           │   │   ├── Rock.java
│   │           │   │   └── Tree.java
│   │           │   └── WorldMap.java
│   │           ├── Main.java
│   │           ├── Simulation.java
│   │           ├── SimulationController.java
│   │           ├── Utils
│   │           │   ├── Actions
│   │           │   │   ├── Action.java
│   │           │   │   ├── CheckAliveEntityAction.java
│   │           │   │   ├── Config.java
│   │           │   │   ├── ConfigLoader.java
│   │           │   │   ├── Generator.java
│   │           │   │   ├── MoveCreaturesAction.java
│   │           │   │   ├── MovementStrategyChooseAction.java
│   │           │   │   └── PlaceEntitiesAction.java
│   │           │   ├── MovementStrategy.java
│   │           │   ├── PathFinderService.java
│   │           │   ├── RandomMoveStrategy.java
│   │           │   ├── Renderer.java
│   │           │   └── TargetMoveStrategy.java
│   │           └── config.json
│   └── test
│       └── java
│           └── Game
│               └── PathFinderTest.java
```
---

## Пример Работы

![Пример анимации симуляции](https://github.com/user-attachments/assets/6b0eba7d-855f-43b4-85b5-adad4348d09d)

---

## Диаграмма на доработке
![SVG](https://www.plantuml.com/plantuml/svg/lHXTRnj5yDs_OWaFkCInn2iAfcrAIwMsLBKH3uY7pNdY7BaFQs_FZO58JG98LH51uWLLGeX_4DAaTMbZ_iBUFs9sHtktldMSLWbKLRittpCx7xE-caakP1v7ZNVa9iJ0Hk6wu8bilCS-ElCF8jKVwb3zhKwAFNQBnz3-996X325Rp3VamHOV05kQuLbYtpOOmxyezXVrLx5Rx1MFjFXYJvqMJzHBXffUgKD4x39rh5uL1qmT5Rke_GU6mFtYiJfLKysdJjHB5CJNCobu85aGyInZHj7OQ65iPIrDHJzCk8IC1TNP89S781ryymhxaAtLQR9P6kUioKTfs2SwcNQb29E1aK97HEoKllwEraxK2_LQdQ2V1_ZlwxgtU9ogAKRZ62bF4RY7hEIKwmPvjXmcacrNn_6CjjzG1yQdU5HyhwFaXFAvcc0udrJ1F36XF48-50DYWmV0xeGZkCTbBW2rsL1z2ZoIcyO_EiveVEfTsWiyQ6-cgE-dug3qXPZj1MdROxvDm0gqQRyztOWbCEz_dMSMC-9H3eITlHSjw6nicDq0tZTorzCq0fwSuzGpDKNO53yFAuVoP236DOS8w3X4tzOXGizpY32koIr6qIW3F-k3BkeHD6iqBSRZD4_aVAz9oGA_3p5rzjGXmtoTwFfiCQpHPtWwmH0W15Fdv-8naXtKYt9InkMBL4Jzktne8uF7LIUHMunAjSF0_8zeCcyOyG0-I_2xIHYqaaWTUucq3n58k2dIU3wrutISZe3WTReMI-2XCIn3sFmAfriZ-kjZ8cu6P_0G6N6jzXXiIqYGphhxPe9QJ8w7e64wcAkzZfRS8w516aKGo1uNLfPrgkVpU67lTTpBNj316JkxzxTlZ1CUXy7vCjmvuFFJF32slDbCM1D0dOcgyhdkfxhJgGaI7UfyHL7ULB1S_dNKlbaO3oE8CPgPtmOnYQbot79689MSsxHi8y2awmu1-XPOraJCXxSJBDqa08kpbyOsqhHak8iIj3LTz4R2OCpY6e08CR_v5cZYffErTeBLXJGpIGSahUDgxEyEHHf0bjr4csud4dHCmpHfQXjrGhha2mR5dBxdTCYTAWNiZ13huIWLO6-dki1R0WawzaBc3zutCjq8SittpRWleCzbAdmhIWClmv9Ic6VDXQkqAxcyG94IEzPe5HIAMxrgpWt2ZYFXGHfiNKX0Jm2yZO0Bn_Dp6KRPMqQHcEhzpT_CRBj3i1ckQgBxsX4UgBTDpExah6TUoP8AE-IQGIu4OklZ13iEWcm3yfVTsXtnf9_6MfFLudR6_yC2ccwk1NxpNcoJrrYb4RceGI_xMfl4vKpLLiN6QBswBEyNPuewfWE2TNgyhAN5DTDKggk0RGXo2KrFSQtZsH6v2S7MjIYqQnA9SoDyCGNU1Qrjfca6bwxclbuVRGVxJvomK-0ofRiESBbvCqpw8BeWHc40bRWv5R21bFU0epLs4pIlV6-Pz9FOjwbdzGJQGz26B99sVeRV2JFfBf-T-Hu_0Eqj27Jq_3rfsL8t7PohjtGYmh1qqHL8lAL861b8xM2y2lpLxfQcEK-n4f_OXs2ngwRcXNj87Nh-JnFU6deB4X1we_0lihha0H418SvyPVJ8uiQCoK_Hh5Di61DyhEqRmtyqvkcXyeAUSCK-dd1LTai_JJR2GMK11hORVWDwEwIJHQppYABDhfkJHRJRRRB64tedvHZeIdI4tuHf3Z6zoX6jFIAuiotQAYPbvfqtdvTUEPJQ7zSZss8tqdmzWiv5rm3MnFdy2a57-fxL2LF_w7S7WgPsLgkJAs-v9imkLzqmpYDEFSIhXOSf7IoYB1i1T70d6pQe95lJFm5Og2b6Qb0uE9DGqZb3-7mQBSAdMKumlZr4-KML-x0XppFmAI7BOn-SoTGho7cnmIIG8eqYIYnVkT-rUPWuwOrVJUnWMPb1NturP_NUQPpxm-YVwgWum7AQCFF3tdDrNEmxZrLwZowMGadALhzRMIcV5mrx8B1z7zJXzddYog3rt8MOTTi5cFMvKSbgjplURCPeuBrb5T7AIgVii8ZSv1dZZHAmzC4IUnybByKy6IynKePa0LuK1ZjhD6okrWIHbkfcwsW-60WOSEaJMO4BYIXxaOJcC17uIQH97Kwo6t5PmoGEeNq-93JJ4TqZHHZUTlkCHSebMRWAkOJ-CkC9yjQHnbMCJ1v7_m80)
