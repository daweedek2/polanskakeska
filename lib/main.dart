import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

Future<TipResult> submitAnswer(String cacheNumber, String teamName, String answer) async {
  var url = Uri.parse('http://localhost:8088/verify');

  final response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(<String, String>{
      'cacheNumber': cacheNumber,
      'teamName': teamName,
      'answer': answer,
    }),
  );

  if (response.statusCode == 201) {
    return TipResult.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Failed to create album.');
  }
}

class TipResult {
  final bool isCorrect;
  final String code;
  final String message;

  TipResult({this.isCorrect, this.code, this.message});

  factory TipResult.fromJson(Map<String, dynamic> json) {
    return TipResult(
      isCorrect: json['isCorrect'],
      code: json['code'],
      message: json['message'],
    );
  }
}

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final appTitle = 'Polanská keška 2021';

    return MaterialApp(
      title: appTitle,
      home: Scaffold(
        appBar: AppBar(
          title: Text(appTitle),
        ),
        body: MyCustomForm(),
      ),
    );
  }
}

// Create a Form widget.
class MyCustomForm extends StatefulWidget {
  @override
  MyCustomFormState createState() {
    return MyCustomFormState();
  }
}

// Create a corresponding State class.
// This class holds data related to the form.
class MyCustomFormState extends State<MyCustomForm> {
  // Create a global key that uniquely identifies the Form widget
  // and allows validation of the form.
  //
  // Note: This is a GlobalKey<FormState>,
  // not a GlobalKey<MyCustomFormState>.
  final _formKey = GlobalKey<FormState>();

  final answerController = TextEditingController();
  final teamController = TextEditingController();
  final cacheController = TextEditingController();

  Future<TipResult> _tipResult;

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    answerController.dispose();
    teamController.dispose();
    cacheController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    // Build a Form widget using the _formKey created above.
    return Form(
      key: _formKey,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          TextFormField(
            controller: teamController,
            decoration: const InputDecoration(
                icon: Icon(Icons.person),
            labelText: 'Jméno týmu',
          ),
            // The validator receives the text that the user has entered.
            validator: (value) {
              if (value == null || value.isEmpty) {
                return 'Tým nesmí bt prázdný.';
              }
              return null;
            },
          ),
          TextFormField(
            controller: cacheController,
            decoration: const InputDecoration(
            icon: Icon(Icons.foundation),
            labelText: 'Číslo kešky',
            ),
            // The validator receives the text that the user has entered.
            validator: (value) {
              if (value == null || value.isEmpty) {
                return 'Keška nesmí bt prázdná.';
              }
              return null;
            },
          ),
          TextFormField(
            controller: answerController,
            decoration: const InputDecoration(
              icon: Icon(Icons.double_arrow),
              labelText: 'Odpověď',
            ),
            // The validator receives the text that the user has entered.
            validator: (value) {
              if (value == null || value.isEmpty) {
                return 'Odpověď nesmí bt prázdná.';
              }
              return null;
            },
          ),
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 16.0),
            child: ElevatedButton(
              onPressed: () {
                // Validate returns true if the form is valid, or false otherwise.
                if (_formKey.currentState.validate()) {
                  // If the form is valid, display a snackbar. In the real world,
                  // you'd often call a server or save the information in a database.
                  String responseMessage;
                  submitAnswer(cacheController.text, teamController.text, answerController.text)
                  .whenComplete(() => print('request is complete'))
                      .then((value) => responseMessage = value.message);
                  ScaffoldMessenger.of(context)
                          .showSnackBar(SnackBar(content: Text(responseMessage)));
                }
              },
              child: Text('Odeslat'),
            ),
          ),
        ],
      ),
    );
  }
}