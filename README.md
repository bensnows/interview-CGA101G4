# CGA101G4


1. pom.xml增加 servlet-api, e-mail

2. 將 JDBC連線, JNDI 參數都改成 從 DBUtil取得

3. 測試執行

```bash
mvn clean package -Dmaven.test.skip=true -D

```

4. 增加deployment.yml
