# BlueTruck

**BlueTruck** is a lightweight, open-source Spring Boot library inspired by the children's book *Little Blue Truck*. It adds a simple, yet delightful feature to your Spring Boot application: a beep sound whenever the server starts successfully on specific profiles. This can be especially useful for local development and debugging.

This is my first take on open source library. 

Help spread the joy/beeps of **BlueTruck** by starring the repository and sharing it with your friends!

---

## Features

- Plays a customizable beep sound after the Spring Boot server starts successfully.
- Configurable profiles to restrict the beep to development environments (e.g., `dev`, `local`).
- Adjustable beep duration and frequency.
- Lightweight and easy to integrate.

---

## Getting Started

### Adding BlueTruck to Your Project

#### Gradle
Add the following dependency to your `build.gradle`:
```groovy
implementation ("com.rathnas:bluetruck:0.0.12")
```

#### Maven
Include the dependency in your `pom.xml`:
```xml
<dependency>
    <groupId>com.rathnas</groupId>
    <artifactId>bluetruck</artifactId>
    <version>0.0.12</version>
</dependency>
```

---

## Configuration

BlueTruck provides the following configuration properties to customize its behavior:

| Property                           | Default Value      | Description                                                                                                                                              |
|------------------------------------|--------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| `com.rathnas.bluetruck.profiles`   | `dev, local, localhost` | Profiles on which the beep sound should be enabled. Separate multiple profiles with commas.                                                              |
| `com.rathnas.bluetruck.duration`   | `200`              | Duration of the beep sound in milliseconds.                                                                                                              |
| `com.rathnas.bluetruck.frequency`  | `200`              | Frequency of the beep sound in hertz.                                                                                                                    |

### Example `application.properties`
```properties
com.rathnas.bluetruck.profiles=dev, local, localhost
com.rathnas.bluetruck.duration=300
com.rathnas.bluetruck.frequency=400
```

### Example `application.yml`
```yaml
com:
  rathnas:
    bluetruck:
      profiles: dev, local, localhost
      duration: 300
      frequency: 400
```

---

## How It Works

1. When a Spring Boot application with BlueTruck starts, the library checks the active Spring profiles.
2. If any of the profiles match those specified in `com.rathnas.bluetruck.profiles`, the library will emit a beep sound after the server starts successfully.
3. The beep's duration and frequency are determined by the `com.rathnas.bluetruck.duration` and `com.rathnas.bluetruck.frequency` properties.

---

## Example Usage

### Minimal Setup
By default, BlueTruck is preconfigured for local development. Simply add the dependency to your project, and you'll hear a beep after the server starts if you're using one of the default profiles (`dev`, `local`, `localhost`).

### Custom Setup
Override the default properties in your `application.properties` or `application.yml` to fit your specific needs, such as using custom profiles or adjusting the beep sound.

---

## Why BlueTruck?

BlueTruck is inspired by the *Little Blue Truck* book and its cheerful "beep!" It brings a tiny but joyful feature to your local development workflow, ensuring you're instantly alerted when your application is ready.

---

## Contributing

Contributions are welcome! If you'd like to add features, fix bugs, or improve documentation, feel free to fork the repository and submit a pull request.

---

## License

BlueTruck is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute it as per the terms of the license.

---

## Support

For issues, feature requests, or other questions, please raise an issue on the [GitHub repository](https://github.com/rathnas/bluetruck).

---

### Acknowledgments
- Inspired by the book *Little Blue Truck* by Alice Schertle.
- Built with ❤️ for developers who enjoy little surprises in their workflow. 