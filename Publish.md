# Publishing to public repo

Although `gradle` is much concise, `Maven` is better supported than `gradle` for publishing to [central](https://central.sonatype.com)

```sh
gpg --full-generate-key
gpg --keyserver keys.openpgp.org --send-keys FC5AAB849965AACA3FFF275F77FA670740195150
gpg --list-secret-keys --keyid-format LONG
gpg --list-keys --keyid-format LONG
mvn clean deploy -e -s ./settings.xml

```