import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //final wordPair = WordPair.random();

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: RandomWord(),
    );
  }
}

class RandomWord extends StatefulWidget {
  const RandomWord({Key? key}) : super(key: key);

  @override
  State<RandomWord> createState() => _RandomWordState();
}

class _RandomWordState extends State<RandomWord> {
  final List<WordPair> suggestions = <WordPair>[];
  final TextStyle biggerfont = TextStyle(fontSize: 18);

  //vamos a crear una variable de tipo Set. Aqui se graba las palabras fav.
  final Set<WordPair> saved = Set<WordPair>();

  @override
  Widget build(BuildContext context) {
    final wordPair = WordPair.random();
    return Scaffold(
      appBar: AppBar(
        title: Text("StartUp Name Generator!!"),
        actions: <Widget>[
          IconButton(onPressed: pushSaved, icon: Icon(Icons.legend_toggle_sharp))
        ],
      ),
      body: builSuggestions(),
    );
  }

  Widget builSuggestions(){
    return ListView.builder(
      padding: EdgeInsets.all(5),
        itemBuilder: (BuildContext context, int i){
        if (i.isOdd){
          return Divider();
    }
        final int index = i ~/ 2;
        //print(index);

        if (index >= suggestions.length){
          suggestions.addAll(generateWordPairs().take(10));
    }
        return buildRow(suggestions[index]);
    }
    );
  }

  Widget buildRow(WordPair pair) {
    final bool alreadySaved = saved.contains(pair);
    return ListTile(
      title: Text(
        pair.asPascalCase,
        style: biggerfont,
      ),
      trailing: Icon(
        alreadySaved ? Icons.favorite : Icons.favorite_border,
        color: alreadySaved ? Colors.red : null,
      ),
      onTap: (){
        setState(() {
          alreadySaved ? saved.remove(pair) : saved.add(pair);
        });
      },
    );
  }

  void pushSaved() {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
          builder: (BuildContext context){
            final Iterable<ListTile> tiles = saved.map(
                (WordPair pair){
                  return ListTile(
                    title: Text(
                      pair.asPascalCase,
                      style: biggerfont,
                    ),
                  );
                }
            );
            final List<Widget> divided = ListTile.divideTiles(
              context: context,
              tiles: tiles,
            ).toList();
            return Scaffold(
              appBar: AppBar(
                title: Text("Saved favorite Names"),
              ),
              body: ListView(
                children: divided),
            );
          }),
    );
  }
}