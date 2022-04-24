
import java.util.Scanner;


class Main {

    // buat simpen data hasil simplify
    public static String HasilHitungan= "";

    public static void main(String[] args) {
        // ini buat handle data
        String InputOne, InputTwo,AllChar;
        Scanner keyboard = new Scanner(System.in);

        // masukin data input
        System.out.print("Input One: ");
        InputOne = keyboard.nextLine();
        System.out.print("Input Two: ");
        InputTwo = keyboard.nextLine();

        //convert ke substractive dulu
        InputOne = ConvertToAddictive(InputOne);
        InputTwo = ConvertToAddictive(InputTwo);

        //masukin 2 input kedalam 1 string
        AllChar = AddTwoString(InputOne,InputTwo);
        System.out.println("Sebelum 1 : "+ AllChar);

        //Sorting comparator sesuai data
        AllChar = SortComparator(AllChar);
        System.out.println("Sebelum 2 : "+ AllChar);

        //convert dulu ke number simplify
        AllChar = ConvertNumber(AllChar);
        System.out.println("HASIL SIMPLIFY : "+ AllChar);

        //convert lagi ke substractive
        AllChar = ConverBackToSubtractive(AllChar);
        System.out.println("Hasil Akhir : " + AllChar);
    }

    //ini buat nyambungin 2 string kedalam 1 string (opsi method)
    public static String AddTwoString(String dataone,String datatwo){
        return dataone + datatwo;
    }

    //ini buat convert number untuk di simplify
    public static String ConvertNumber(String current)
    {
        //ini text sebelum
        char currentText[] = current.toCharArray();
        // buat simpen data char yang di check
        char LastCharacter = '\0';
        // buat simpen data score yang di check
        int currentScore = 0;
        // buat simpen data berapa banyak yg di check
        int maxLength = 0;

        // buat simpen data simplify sementara
        String TempHasil="";

        //loop dari belakang
        for(int x = currentText.length-1 ;x >= 0 ; x-- )
        {
            //ketika ini angka pertama yg di check
            if(x == currentText.length-1)
            {
                LastCharacter = currentText[x];
                currentScore = CalculateScoreNumber(LastCharacter);
                maxLength += 1;

                TempHasil = "" + LastCharacter;
            }
            else
            {
                //ketika character yang dicheck sama, berarti tinggal masukin aja lagi ke temp
                if(LastCharacter == currentText[x])
                {
                    TempHasil = LastCharacter + TempHasil;
                    maxLength += 1;
                }
                //ketika character yang di check beda
                else
                {
                    //check simplify sesuai hasil sekarang
                    TempHasil = CalculateNumberRoman(TempHasil, currentScore, maxLength);

                    //check apakah temp yang baru itu beda ga sama yang sekarang , misal CCCC berubah jadi D, nah si D ini sama ga sama yg sekarang kalau sama, berarti tinggal tambahin
                    if(TempHasil.charAt(0) == currentText[x])
                    {
                        LastCharacter = currentText[x];
                        currentScore = CalculateScoreNumber(LastCharacter);
                        maxLength = 2;
                        TempHasil = "" +LastCharacter+TempHasil.charAt(0);
                    }
                    // kalau misalnya beda, berarti bikin baru lalu tambahin temp yang tadi ke hasil
                    else
                    {
                        HasilHitungan = TempHasil + HasilHitungan;
                        LastCharacter = currentText[x];
                        currentScore = CalculateScoreNumber(LastCharacter);
                        maxLength = 1;
                        TempHasil = "" +LastCharacter;
                    }
                }
                // ini misalnya kalau hasil terakhir, langsung check aja lagi
                if(x == 0)
                {
                    TempHasil = CalculateNumberRoman(TempHasil, currentScore, maxLength);
                    HasilHitungan = TempHasil + HasilHitungan;
                }
            }
        }
        return HasilHitungan;
    }

    // ini buat check, character yang di check itu 'score'nya berapa, jadi nanti tinggal dikali aja
    public static int CalculateScoreNumber(char character)
    {
        if(character == 'I')
        {
            return 1;
        }
        if(character == 'V')
        {
            return 5;
        }
        if(character == 'X')
        {
            return 10;
        }
        if(character == 'L')
        {
            return 50;
        }
        if(character == 'C')
        {
            return 100;
        }
        if(character == 'D')
        {
            return 500;
        }
        if(character == 'M')
        {
            return 1000;
        }

        return 0;
    }

    // ini buat calculate simplify number
    public static String CalculateNumberRoman(String curData, int score,int length)
    {
        // ini hitung kemampuan sampai mana simplify berjalan
        int MaxScore=0;
        int SelectedRange=0;
        for(int x=0;x<curData.length();x++)
        {
            int currentDataScore = CheckNumberSimplify(score,x+1);

            //kalau misalnya lebih berarti naikin kemampuan simplyfi
            if(currentDataScore >= MaxScore)
            {
                MaxScore = currentDataScore;
                SelectedRange = x+1;
            }
        }
        if(MaxScore == score && SelectedRange == length)
        {
            return curData;
        }
        //masukin hasil sisa hitungan
        HasilHitungan = curData.substring(SelectedRange, length) + HasilHitungan;

        if(MaxScore == 1){
            return "I";
        }
        if(MaxScore == 5){
            return "V";
        }
        if(MaxScore == 10){
            return "X";
        }
        if(MaxScore == 50){
            return "L";
        }
        if(MaxScore == 100){
            return "C";
        }
        if(MaxScore == 500){
            return "D";
        }
        if(MaxScore == 1000){
            return "M";
        }
        return curData;
    }
    // ini buat check simplify numbernya, check apakah hasilnya ketika di mod number roman itu bakal sama ga, kalau sama tinggal check aja
    public static int CheckNumberSimplify(int score,int length)
    {
        if(score*length % 5 == 0)
        {
            if(score*length % 10 == 0)
            {
                if(score*length % 50 == 0)
                {
                    if(score*length % 100 == 0)
                    {
                        if(score*length % 500 == 0)
                        {
                            if(score*length % 1000 == 0)
                            {
                                return 1000;
                            }   else return 500;
                        }else return 100;
                    }else return 50;
                }else return 10;
            }else return 5;
        }else return 1;
    }

    // ini buat convert subtractive ke addictive
    public static String ConvertToAddictive(String data)
    {
        String CurrentText = data;

        if(CurrentText.contains("CM"))
        {
            CurrentText = CurrentText.replace("CM", "DCCCC");
        }

        if(CurrentText.contains("CD"))
        {
            CurrentText =CurrentText.replace("CD", "CCCC");
        }
        
        if(CurrentText.contains("XC"))
        {
            CurrentText = CurrentText.replace("XC", "LXXXX");
        }
        
        if(CurrentText.contains("XL"))
        {
            CurrentText = CurrentText.replace("XL", "XXXX");
        }
        
        if(CurrentText.contains("IX"))
        {
            CurrentText = CurrentText.replace("IX", "VIIII");
        }
        
        if(CurrentText.contains("IV"))
        {
            CurrentText = CurrentText.replace("IV", "IIII");
        }

        return CurrentText;
    }

    // ini buat sorting sesudah besaran data
    public static String SortComparator(String currentData)
    {
        char arrayComparator[]  = {'M','D','C','L','X','V','I'};
        char tempArray[] = currentData.toCharArray();

        String tempHasil = "";

        for (char compare : arrayComparator) 
        {
            for (char cur : tempArray) 
            {
                if(cur == compare)
                {
                    tempHasil += compare;
                }    
            }
        }
        return tempHasil;
    }
    // ini buat balikin dari addictive ke subtractive
    public static String ConverBackToSubtractive(String data){
        String AllChar = data;
        boolean checked = false;
        do
        {
            checked = false;

            if(AllChar.contains("DCCCC"))
            {
                AllChar = AllChar.replace("DCCCC", "CM");
                checked = true;
            }
            
            if(AllChar.contains("CCCC"))
            {
                AllChar =  AllChar.replace("CCCC", "CD");
                checked = true;
            }
            
            if(AllChar.contains("LXXXX"))
            {
                AllChar = AllChar.replace("LXXXX", "XC");
                checked = true;
            }
            
            if(AllChar.contains("XXXX"))
            {
                AllChar =  AllChar.replace("XXXX", "XL");
                checked = true;
            }
            
            if(AllChar.contains("VIIII"))
            {
                AllChar = AllChar.replace("VIIII", "IX");
                checked = true;
            }

            if(AllChar.contains("IIII"))
            {
                AllChar = AllChar.replace("IIII", "IV");
                checked = true;
            }

        }while(checked);

        return AllChar;
    }
}