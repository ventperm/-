package ru.orion.autoservice
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import org.json.JSONArray
import org.json.JSONObject

data class Order(val id:Long,val client:String,val car:String,val issue:String,val cost:Long,val status:Int=0)
val stages=listOf("Приём","Диагностика","Согласование","Ремонт","Готово","Выдан")
class MainActivity:ComponentActivity(){
 override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState)
 val prefs=getSharedPreferences("service",MODE_PRIVATE)
 val records=mutableStateListOf<Order>()
 runCatching { val a=JSONArray(prefs.getString("orders","[]")); for(i in 0 until a.length()){val o=a.getJSONObject(i); records.add(Order(o.getLong("id"),o.getString("client"),o.getString("car"),o.getString("issue"),o.getLong("cost"),o.getInt("status"))) } }
 fun save(){val a=JSONArray();records.forEach{a.put(JSONObject().put("id",it.id).put("client",it.client).put("car",it.car).put("issue",it.issue).put("cost",it.cost).put("status",it.status))};prefs.edit().putString("orders",a.toString()).apply()}
 setContent { MaterialTheme(colorScheme=darkColorScheme(primary=Color(0xFF80B9FF),background=Color(0xFF101923),surface=Color(0xFF192738))){
 var tab by remember { mutableStateOf(0) }; var add by remember { mutableStateOf(false) }; var search by remember { mutableStateOf("") }
 Surface(Modifier.fillMaxSize()){ Column(Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding().padding(16.dp)){
 Text("ORION AUTO SERVICE",style=MaterialTheme.typography.headlineSmall); Text("Рабочее место автосервиса • 0.1")
 NavigationBar { listOf("Обзор","Заказы","Агент").forEachIndexed{i,s->NavigationBarItem(selected=tab==i,onClick={tab=i},icon={Text(listOf("01","02","03")[i])},label={Text(s)})} }
 when(tab){
 0->{Text("Активные заказы: "+records.count{it.status<5},style=MaterialTheme.typography.titleLarge);Text("Выдано: "+records.count{it.status==5});Text("Сумма заказов: "+records.sumOf{it.cost}+" ₽");Button(onClick={add=true}){Text("Принять автомобиль")}}
 1->{OutlinedTextField(search,{search=it},label={Text("Поиск клиента или автомобиля")},modifier=Modifier.fillMaxWidth());Button(onClick={add=true}){Text("Новый заказ")};LazyColumn(verticalArrangement=Arrangement.spacedBy(8.dp)){items(records.filter{(it.client+" "+it.car+" "+it.issue).contains(search,true)},key={it.id}){o->Card{Column(Modifier.padding(12.dp)){Text(o.car,style=MaterialTheme.typography.titleLarge);Text(o.client);Text(o.issue);Text("${o.cost} ₽ • ${stages[o.status]}");if(o.status<5) Button(onClick={val i=records.indexOfFirst{it.id==o.id};if(i>=0){records[i]=o.copy(status=o.status+1);save()}}){Text("Далее: "+stages[o.status+1])}}}}}}
 2->{Text("Помощник диагностики",style=MaterialTheme.typography.titleLarge);Text("Локальный справочный алгоритм, не нейросеть");var symptom by remember{mutableStateOf("")};var answer by remember{mutableStateOf("")};OutlinedTextField(symptom,{symptom=it},label={Text("Симптом автомобиля")},modifier=Modifier.fillMaxWidth());Button(onClick={answer=when{symptom.contains("не завод",true)->"Проверить заряд АКБ, питание, ошибки ЭБУ и условия запуска. Не заменять детали без измерений.";symptom.contains("перегрев",true)->"Остановить двигатель безопасно. Не открывать горячую систему охлаждения. После остывания проверить уровень жидкости и герметичность.";else->"Уточнить условия проявления, провести визуальный осмотр, считать ошибки и проверить параметры по документации производителя."}},enabled=symptom.isNotBlank()){Text("План проверки")};Text(answer)}
 }
 }
 }
 if(add){var client by remember{mutableStateOf("")};var car by remember{mutableStateOf("")};var issue by remember{mutableStateOf("")};var cost by remember{mutableStateOf("")};AlertDialog(onDismissRequest={add=false},title={Text("Приём автомобиля")},text={Column{OutlinedTextField(client,{client=it},label={Text("Клиент")});OutlinedTextField(car,{car=it},label={Text("Автомобиль / госномер")});OutlinedTextField(issue,{issue=it},label={Text("Жалоба")});OutlinedTextField(cost,{cost=it},label={Text("Стоимость, ₽")})}},confirmButton={TextButton(enabled=client.isNotBlank()&&car.isNotBlank()&&issue.isNotBlank()&&(cost.toLongOrNull()?:-1)>=0,onClick={records.add(Order(System.currentTimeMillis(),client,car,issue,cost.toLong()));save();add=false;tab=1}){Text("Сохранить")}},dismissButton={TextButton(onClick={add=false}){Text("Отмена")}})}
 }}
 }
}