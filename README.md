# Dependency Injection Project

This project demonstrates various implementations of dependency injection patterns in Java, from basic static coupling to advanced Spring Framework implementations.

## Overview

The project showcases four different approaches to dependency injection:

1. Static Coupling
2. Dynamic Instantiation
3. Spring XML Configuration
4. Spring Annotations

## Project Structure

````plaintext
dependency-injection/
├── src/main/java/com/jee/
│   ├── static_instanciation/
│   ├── dynamic_instanciation/
│   ├── spring/
│   │   ├── xml/
│   │   └── annotations/
│   └── resources/
└── config files (config.xml, config.yaml, config.txt)
```

## Implementation Details

### 1. Static Coupling Implementation

This is the most basic implementation where dependencies are directly instantiated within the code.

```java
// Example from static_instanciation
IDao dao = new IDaoImpl();
IMetier metier = new MetierImpl(dao);
System.out.println(metier.calcul());
````

**Explanation:**
- `IDao dao = new IDaoImpl();`: Here, we directly instantiate the `IDaoImpl` class, which implements the `IDao` interface.
- `IMetier metier = new MetierImpl(dao);`: We then pass this `dao` instance to the `MetierImpl` class, which implements the `IMetier` interface.
- `System.out.println(metier.calcul());`: Finally, we call the `calcul()` method on the `metier` instance to perform some business logic.


### 2. Dynamic Instantiation

Uses reflection API to create instances dynamically based on configuration files.

Supports two configuration formats:

YAML Configuration (config.yaml):

```yaml
classes:
  dao: "com.jee.dynamic_instanciation.dao.IDaoImpl"
  metier: "com.jee.dynamic_instanciation.metier.MetierImpl"
```

Text Configuration (config.txt):

```text
com.jee.dynamic_instanciation.dao.IDaoImpl
com.jee.dynamic_instanciation.metier.MetierImpl
```

```java
public static void main(String[] args) throws Exception {
        Map<String, Object> yamlData = getYamlFileData();
        Map<String, String> classes = (Map<String, String>) yamlData.get("classes");
        String daoClassName = classes.get("dao");
        String metierClassName = classes.get("metier");
        IDao dao = (IDao) Class.forName(daoClassName).getConstructor().newInstance();
        IMetier metier = (IMetier) Class.forName(metierClassName).getConstructor().newInstance();
        Method method = metier.getClass().getDeclaredMethod("setDao", IDao.class);
        method.invoke(metier, dao);
        System.out.println(metier.calcul());
    }

    public static Map<String, Object> getYamlFileData() throws Exception {
        Map<String, Object> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("config.yaml"))) {
            String line;
            String currentKey = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.endsWith(":")) {
                    currentKey = line.substring(0, line.length() - 1).trim();
                    data.put(currentKey, new HashMap<String, String>());
                } else if (line.contains(":")) {
                    String[] parts = line.split(":");
                    String key = parts[0].trim();
                    String value = parts[1].trim().replace("'", "");
                    if (currentKey != null) {
                        ((Map<String, String>) data.get(currentKey)).put(key, value);
                    } else {
                        data.put(key, value);
                    }
                }
            }
        }
        return data;
    }
```

**Explanation:**
- `Map<String, Object> yamlData = getYamlFileData();`: Reads the YAML configuration file and returns a map of key-value pairs.
- `Map<String, String> classes = (Map<String, String>) yamlData.get("classes");`: Retrieves the `classes` section from the YAML data.
- `String daoClassName = classes.get("dao");`: Retrieves the value for the `dao` key.
- `String metierClassName = classes.get("metier");`: Retrieves the value for the `metier` key.
- `IDao dao = (IDao) Class.forName(daoClassName).getConstructor().newInstance();`: Creates an instance of the `IDao` implementation using reflection.
- `IMetier metier = (IMetier) Class.forName(metierClassName).getConstructor().newInstance();`: Creates an instance of the `IMetier` implementation using reflection.
- `Method method = metier.getClass().getDeclaredMethod("setDao", IDao.class);`: Retrieves the `setDao` method from the `metier` instance.
- `method.invoke(metier, dao);`: Invokes the `setDao` method on the `metier` instance, passing the `dao` instance as an argument.
- `System.out.println(metier.calcul());`: Calls the `calcul()` method on the `metier` instance.



### 3. Spring XML Configuration

Uses Spring Framework's XML-based dependency injection.

```xml
<beans>
    <bean id="dao" class="com.jee.spring.xml.extensions.DaoImplv2"/>
    <bean id="metier" class="com.jee.spring.xml.metier.MetierImpl">
        <constructor-arg ref="dao"/>
    </bean>
</beans>
```

**Code Example:**
```java
ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
IMetier metier = context.getBean(IMetier.class);
System.out.println(metier.calcul());
```

**Explanation:**
- `<bean id="dao" class="com.jee.spring.xml.extensions.DaoImplv2"/>`: Defines a bean for the `IDao` implementation.
- `<bean id="metier" class="com.jee.spring.xml.metier.MetierImpl">`: Defines a bean for the `IMetier` implementation, injecting the `dao` bean via constructor.
- `ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");`: Loads the Spring context from the XML configuration file.
- `IMetier metier = context.getBean(IMetier.class);`: Retrieves the `metier` bean from the Spring context.
- `System.out.println(metier.calcul());`: Calls the `calcul()` method on the `metier` instance.


### 4. Spring Annotations

Modern approach using Spring's annotation-based configuration.

```java
ApplicationContext context = new AnnotationConfigApplicationContext("com.jee.spring.annotations");
IMetier metier =  context.getBean(IMetier.class);
System.out.println(metier.calcul());
```

**Explanation:**
- `ApplicationContext context = new AnnotationConfigApplicationContext("com.jee.spring.annotations");`: Loads the Spring context using annotation-based configuration.
- `IMetier metier = context.getBean(IMetier.class);`: Retrieves the `metier` bean from the Spring context.
- `System.out.println(metier.calcul());`: Calls the `calcul()` method on the `metier` instance.


## Dependencies

The project uses the following dependencies:

- Spring Core 6.2.3
- Spring Context 6.2.3
- Spring Beans 6.2.3
- Jakarta Annotation API 2.1.1

## How to Run

1. Clone the repository
2. Build using Maven:

```bash
mvn clean install
```

3. Run any of the following main classes:

- Static Implementation: com.jee.static_instanciation.presentation.Presentation
- Dynamic Implementation: com.jee.dynamic_instanciation.presentation.Presentation
- Spring XML: com.jee.spring.xml.presentation.Presentation
- Spring Annotations: com.jee.spring.annotations.presentation.Presentation
