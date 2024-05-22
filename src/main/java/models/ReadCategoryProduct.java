                    
package models;

import java.util.TreeMap;


public class ReadCategoryProduct {
    
    //準備好產品清單  
    public static TreeMap<String, Product> readProduct() {
        //read_product_from_file(); //從檔案或資料庫讀入產品菜單資訊
        //放所有產品  產品編號  產品物件
        TreeMap<String, Product> product_dict = new TreeMap<>();
        String[][] product_array = {
            {"p-j-101", "SEVENTEEN", "1st Mini Album '17 CARAT'", "328", "17_CARAT.png","第1張迷你專輯"},
            {"p-j-102", "SEVENTEEN", "2nd Mini Album 'BOYS BE'", "378", "BOYSBE.png", "第2張迷你專輯"},
            {"p-j-103", "SEVENTEEN", "1st ALBUM [FIRST 'LOVE & LETTER']", "528", "FIRSTLOVELETTER.png", "第1張正規專輯"},
            {"p-j-104", "SEVENTEEN", "Love&Letter Repackage Album", "538", "LoveLetter.png", "改版專輯"},
            {"p-j-105", "SEVENTEEN", "3rd Mini Album 'Going Seventeen'", "468", "GoingSeventeen.png", "第3張迷你專輯"},
            {"p-j-106", "SEVENTEEN", "4th Mini Album 'Al1'", "478", "Al1.png", "第4張迷你專輯"},
            {"p-j-107", "SEVENTEEN", "2ND ALBUM 'TEEN, AGE'", "538", "TEENAGE.png", "第2張正規專輯"},
            {"p-j-108", "SEVENTEEN", "SPECIAL ALBUM 'DIRECTOR'S CUT'", "528", "DIRECTORSCUT.png", "特別專輯"},
            {"p-j-109", "SEVENTEEN", "5th Mini Album 'YOU MAKE MY DAY'", "488", "YOUMAKEMYDAY.png", "第5張迷你專輯"},
            {"p-j-110", "SEVENTEEN", "6th Mini Album 'YOU MADE MY DAWN'", "508", "YOUMADEMYDAWN.png", "第6張迷你專輯"},
            {"p-j-111", "SEVENTEEN", "[3rd Album] An Ode", "528", "AnOde.jpg", "第3張正規專輯"},
            {"p-t-112", "SEVENTEEN", "Special Album '; [Semicolon]'", "498", "Semicolon.jpg", "特別專輯"},
            {"p-t-113", "SEVENTEEN", "8th Mini Album 'Your Choice'", "538", "YourChoice.jpg", "第8張迷你專輯"},
            {"p-t-114", "SEVENTEEN", "9th Mini Album [Attacca]", "528", "Attacca.png", "第9張迷你專輯"},
            {"p-t-115", "SEVENTEEN", "4th Album 'Face the Sun'", "548", "FacetheSun.png", "第4張正規專輯"},
            {"p-t-116", "SEVENTEEN", "4th Album Repackage 'SECTOR 17'", "618", "SECTOR17.png", "改版專輯"},
            {"p-t-117", "SEVENTEEN", "10th Mini Album 'FML'", "548", "FML.png", "第10張迷你專輯"},
            {"p-t-118", "SEVENTEEN", "11th Mini Album 'SEVENTEENTH HEAVEN'", "598", "SEVENTEENTHHEAVEN.png", "第11張迷你專輯"},
            {"p-t-119", "SEVENTEEN", "SEVENTEEN BEST ALBUM '17 IS RIGHT HERE'", "1028", "17ISRIGHTHERE.png", "最新專輯"},
            {"p-t-120", "BTS", "2 COOL 4 SKOOL", "358", "4SKOOL.jpg", "單曲專輯"},
            {"p-t-121", "BTS", "O!RUL8,2?", "398", "ORUL82.jpg", "第1張迷你專輯"},
            {"p-t-122", "BTS", "Skool Luv Affair", "398", "SkoolLuvAffair.jpg", "第2張迷你專輯"},
            {"p-t-123", "BTS", "Dark & Wild", "498", "DarkWild.jpg", "第1張正規專輯"},
            {"p-t-124", "BTS", "花樣年華 pt.1", "458", "TheMostBeautifulMomentinLifePT1.jpg", "第3張迷你專輯"},
            {"p-t-125", "BTS", "花樣年華 pt.2", "458", "TheMostBeautifulMomentinLifePT2.jpg", "第4張迷你專輯"},
            {"p-t-126", "BTS", "WINGS", "498", "WINGS.jpg", "第2張正規專輯"},
            {"p-t-127", "BTS", "YOU NEVER WALK ALONE", "588", "YOUNEVERWALKALONE.jpg", "特別專輯"},
            {"p-t-128", "BTS", "LOVE YOURSELF 承 'Her'", "498", "Her.jpg", "第5張迷你專輯"},
            {"p-t-129", "BTS", "LOVE YOURSELF 轉 'Tear'", "558", "Tear.jpg", "第3張正規專輯"},
            {"p-t-130", "BTS", "LOVE YOURSELF 結‘Answer'", "598", "Answer.jpg", "特別專輯"},
            {"p-t-131", "BTS", "MAP OF THE SOUL : PERSONA", "478", "PERSONA.jpg", "第6張迷你專輯"},
            {"p-t-132", "BTS", "Map Of The Soul : 7", "588", "MapOfTheSoul7.jpg", "第4張正規專輯"},
            {"p-t-133", "BTS", "BE", "578", "BE.jpg", "第7張迷你專輯"},
            {"p-t-134", "BTS", "Butter", "538", "Butter.jpg", "單曲專輯"},
            {"p-t-135", "BTS", "Proof", "1588", "Proof.png", "精選專輯"},
            {"p-t-136", "TXT", "The Dream Chapter: STAR", "468", "STAR.jpg", "第1張迷你專輯"},
            {"p-t-137", "TXT", "The Dream Chapter: MAGIC", "498", "MAGIC.jpg", "第1張正規專輯"},
            {"p-t-138", "TXT", "The Dream Chapter : Eternity", "498", "Eternity.jpg", "第2張迷你專輯"},
            {"p-t-139", "TXT", "minisode1 : Blue Hour", "498", "BlueHour.jpg", "第3張迷你專輯"},
            {"p-t-140", "TXT", "The Chaos Chapter: FREEZE", "498", "FREEZE.jpg", "第1張正規專輯"},
            {"p-t-141", "TXT", "The Chaos Chapter: FIGHT OR ESCAPE", "538", "FIGHTORESCAPE.jpg", "改版專輯"},
            {"p-t-142", "TXT", "minisode2: Thursday's Child", "478", "ThursdaysChild.png", "第4張迷你專輯"},
            {"p-t-143", "TXT", "The Name Chapter: TEMPTATION", "498", "TEMPTATION.png", "第5張迷你專輯"},
            {"p-t-144", "TXT", "The Name Chapter: FREEFALL", "568", "FREEFALL.png", "第3張正規專輯"},
            {"p-t-145", "TXT", "minisode 3: TOMORROW", "538", "TOMORROW.png", "第6張迷你專輯"}
        };

        //一筆放入字典變數product_dict中
        for (String[] item : product_array) {
            Product product = new Product(
                    item[0], 
                    item[1], 
                    item[2], 
                    Integer.parseInt(item[3]), //價格轉為int
                    item[4], 
                    item[5]);
            //將這一筆放入字典變數product_dict中 
            product_dict.put(product.getProduct_id(), product);
        }
        return product_dict; 
    }
}