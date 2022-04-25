
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
        System.out.println("Add two String : "+ AllChar);

        //Sorting comparator sesuai data
        AllChar = SortComparator(AllChar);
        System.out.println("Sort Data : "+ AllChar);

        //convert dulu ke number simplify
        AllChar = SimplifyData(AllChar);
        System.out.println("HASIL SIMPLIFY : "+ AllChar);

        //convert lagi ke substractive
        AllChar = ConverBackToSubtractive(AllChar);
        System.out.println("Hasil Akhir : " + AllChar);
    }

    //ini buat nyambungin 2 string kedalam 1 string (opsi method)
    public static String AddTwoString(String dataone,String datatwo){
        return dataone + datatwo;
    }

    // ini buat ubah data simplify
    public static String SimplifyData(String data)
    {
        String CurrentText = data;

        if(CurrentText.contains("IIIII"))
        {
            CurrentText = CurrentText.replace("IIIII", "V");
        }

        if(CurrentText.contains("VV"))
        {
            CurrentText =CurrentText.replace("VV", "X");
        }
        
        if(CurrentText.contains("XXXXX"))
        {
            CurrentText = CurrentText.replace("XXXXX", "L");
        }
        
        if(CurrentText.contains("LL"))
        {
            CurrentText = CurrentText.replace("LL", "C");
        }
        
        if(CurrentText.contains("CCCCC"))
        {
            CurrentText = CurrentText.replace("CCCCC", "D");
        }
        
        if(CurrentText.contains("DD"))
        {
            CurrentText = CurrentText.replace("DD", "M");
        }

        return CurrentText;
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