import 'package:drift/drift.dart';
import 'package:drift/native.dart';
import 'package:path/path.dart' as p;
import 'package:path_provider/path_provider.dart';
import 'dart:io';

part 'database.g.dart';

class Users extends Table{
  IntColumn get id => integer().autoIncrement()();
  TextColumn get nombre => text().named('nombre')();
  TextColumn get correo => text()();
}

LazyDatabase openConecction(){
  return LazyDatabase(()async{
    final dbfolder = await getApplicationDocumentsDirectory();
    final file = File(p.join(dbfolder.path,'db.sqlite'));
    return NativeDatabase(file);
  });
}

@DriftDatabase(tables:[Users])
class AppDatabase extends _$AppDatabase{
  AppDatabase(): super(openConecction());
  @override
  int get schemaVersion => 1;

  //vamos a crear el C R U D
  //list
  Future<List<User>> getListUser() async{
    return await select(users).get();
  }

  //insert
  Future<int> insertUser(UsersCompanion usersCompanion) async{
    return await into(users).insert(usersCompanion);
  }

  //update
  Future<bool> updateUser(UsersCompanion usersCompanion) async{
    return await update(users).replace(usersCompanion);
  }

  //delete
  Future<int> deleteUser(UsersCompanion usersCompanion) async{
    return await delete(users).delete(usersCompanion);
  }
}