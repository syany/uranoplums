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
 
 ```
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
 
 ```
<dependency>
  <groupId>org.uranoplums</groupId>
  <artifactId>uranoplums</artifactId>
  <version>LATEST</version>
  <scope>compile</scope>
</dependency>
 ```
 
 ## リソースバンドル系の使用方法
 通常のリソースバンドル同様にpropertyファイルのほか、JSON、YAML、XMLに対応しました。
