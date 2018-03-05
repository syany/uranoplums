# uranoplums
The on-site oriented standard Java libraries, provides these extra methods.

uranoplums(ウラノプラムス)は、JDKとフレームワーク間や業務アプリとフレームワーク間を支えるためのコンポーネントです。　　
次のような機能を提供しています。

1. Objectの基盤なるようなクラス。
2. 様々なファイル形式のリソースバンドルクラス。
3. 動的にレベルを変えられるようなロガークラス。
4. ユーティリティクラス。（特には文字編集）
	1. 文字編集ユーティリティ。（日本語は全半角変換、ひらカナ変換、ローマ字変換なども含まれています）
	2. ダイジェスト系（md5、sha2、sha512などの型暗号やAES暗号）
	3. オリジナル日付フォーマッタ、カレンダー
	4. リフレクション系
	5. Collection、Map、Arrayのユーティリティ
 
 特に、Webアプリケーション、C/Sアプリケーション、バッチアプリケーションに関わらず共通的に必要とされるであろう機能を1つにまとめています。
 
 ## 環境設定
 
 特には、同github内の[uranoplumsクックブック](https://github.com/uranoplums/uranoplums/blob/master/docs/uranoplums%E3%82%AF%E3%83%83%E3%82%AF%E3%83%96%E3%83%83%E3%82%AF_ver1.0.pdf)に記載しましたが、以下のように設定します
 
 ### Gradleの場合
 
 ```groovy
 // リポジトリURLの追加
repositories {
  maven {
    url "https://github.com/uranoplums/uranoplums/tree/master/build/maven"
  }
}
// 依存関係の設定方法
dependencies {
  compile group: 'org.uranoplums', name: 'uranoplums', version: '1.5'
}
 ```
 
 ### Mavenの場合
 
 ```xml
<dependency>
  <groupId>org.uranoplums</groupId>
  <artifactId>uranoplums</artifactId>
  <version>LATEST</version>
  <scope>compile</scope>
</dependency>
 ```
 
 ## リソースバンドル系の使用方法
 通常のリソースバンドル同様にpropertyファイルのほか、JSON、YAML、XMLに対応しました。
 
 ### yamlの場合
 次のようなyamlファイル(sample.yaml)があったとします。
 
 sample.yaml
 ```yaml
 name: Taro
 hello: Hello $(name) !
 age: 23
 ```
 
 sample_ja.yaml
 ```yaml
 name:太郎
 ```
 
 これらを、次のように実装すると、nameの値は通常のリソースバンドルと同じように日本の場合は「太郎」その他は「Taro」と表示されますし、Antがpropertyファイルを扱うように、helloの値にある$(name)で、同一ファイル内の別キーを取得し「Hello 太郎 !」と表示されます。例えば、途中まで同じ（ディレクトリパスなど）で、以降が異なる値を扱いたい場合などに役に立ちます。
 
 ```java
 UraYAMLResource uraYAMLResource = new UraYAMLResource("sample");
 String name = uraYAMLResource.getResourceString("name");
 System.out.println("name:" + name);
 String hello = uraYAMLResource.getResourceString("hello");
 System.out.println("hello:" + hello);
 ```
