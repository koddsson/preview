REST service to convert docx files to PDF
=========================================

It's all in the title ;)

How do I run this?
------------------

After checking out/forking the repository it should be as simple as:

```
gradle wrapper
./gradlew dependencies build run -DmainClass=poi.TestCon
```

And then you can send `HTTP PUT` requests containing documents to 
`localhost:8182` and the service will respond with a url to the newly created
PDF document.

Here's a example

```
curl -F name=test -F filedata=@HelloWord.docx http://localhost:8182 -XPUT
```

Contributing
------------

Feel free to go bananas. Pull requests, issues, screaming loudly at the computer
are all appreciated. Also I'm [@koddsson](https://twitter.com/koddsson) on twitter so feel free to holla at me
there.
