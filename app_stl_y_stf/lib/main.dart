import 'package:flutter/material.dart';

void main() {
  runApp(myStatefullApp());
}

class myStatelessApp extends StatelessWidget {
  int counter = 0;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Ejm. de Stateless W!!!"),
        ),
        body: Center(
          child: myStatefullApp()
          ),
        ),
        //FAB
        floatingActionButton: FloatingActionButton(
          child: Icon(Icons.plus_one),
          onPressed: () {
            counter++;
            print(counter);
          },
        ),
      ),
    );
  }
}

class myStatefullApp extends StatefulWidget {
  @override
  State<myStatefullApp> createState() => _myStatefullAppState();
}

class _myStatefullAppState extends State<myStatefullApp> {
  int counter = 0;
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Ejm. de Statefull W!!!"),
        ),
        body: Center(
          child: Text(
            counter.toString(),
            style: TextStyle(fontSize: 60),
          ),
        ),
        //FAB
        floatingActionButton: FloatingActionButton(
          child: Icon(Icons.plus_one),
          onPressed: () {
            counter++;
            print(counter);
            setState(() {
            });
          },
        ),
      ),
    );
  }
}