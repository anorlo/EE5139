import java.util.ArrayList;
class BinTree {
    public BinTree Left;
    public BinTree Right;
    public BinTree Root;

    ArrayList List = new ArrayList();
    private float data;

    public BinTree(float data) {
        this.data = data;
        this.Left = null;
        this.Right = null;
        this.Root = null;
    }

    public float getdata() { return data;}
    public void setdata(float newdata) {data = newdata;}
    public BinTree addroot(BinTree cu_left, BinTree cu_right) {
        if (cu_left == null) {
            System.out.println("cu_left is Null!");
            return null;
        }
        if (cu_right == null) {
            System.out.println("cu_right is Null");
            return null;
        }

        BinTree rootTree = new BinTree(cu_left.getdata()+cu_right.getdata());
        rootTree.Left = cu_left;
        rootTree.Right = cu_right;
        return rootTree;
    }

    public void TRecursion(BinTree root){
        if(root != null){
            if(root.Left == null){
                System.out.println(root.getdata()+":"+List);
            }
        }
        if(root != null){
//            System.out.println(root.getdata());
            if(root.Left != null){
                List.add(0);
                TRecursion(root.Left);
                List.remove(List.size() -1);
            }
            if(root.Right != null){
                List.add(1);
                TRecursion(root.Right);
                List.remove(List.size() -1);
            }
        }
    }
}

class TestBinTree{
    public static void main(String[] args){
        float[] num = {0.3f,0.2f,0.1f,0.1f,0.1f,0.1f,0.1f}; //type your array here which sum up to 1
        float[] min = new float[2];
        Maths cal = new Maths();
        BinTree[] ss = new BinTree[(int)num.length/2];
        for(int i=0; i<(int)num.length/2; i=i+1){
            ss[i] = new BinTree(0);
        }
        boolean flag1=true, flag2=true;
        int index1=-1, index2=-1;

        while (cal.whetherstop(ss, (int)num.length/2)){
            min=cal.Findsmall(num);
//            System.out.println(Arrays.toString(num));
            BinTree s1_left = new BinTree(0);
            BinTree s1_right = new BinTree(0);
            for(int i=0; i< (int)num.length/2; i=i+1){
                if(min[0]==ss[i].getdata() && flag1==true){
                    s1_left=ss[i];
                    index1=i;
                    flag1=false;
                    continue;
                }
                if(min[1]==ss[i].getdata() && flag2==true){
                    s1_right=ss[i];
                    index2=i;
                    flag2=false;
                    continue;
                }
            }
            if(flag1==true){ s1_left.setdata(min[0]);}
            if(flag2==true){ s1_right.setdata(min[1]);}

            BinTree s2 = new BinTree(0);
            s2=s2.addroot(s1_left, s1_right);
            if(flag1 == false && flag2 == false){
                ss[index1]=s2;
                ss[index2].setdata(0);
            }
            else{
                if(flag1==false && flag2 == true){
                    ss[index1]=s2;
                }
                if(flag1==true && flag2 == false){
                    ss[index2]=s2;
                }
                if(flag1==true && flag2==true){
                    for(int j=0; j< (int)num.length/2; j=j+1){
                        if(ss[j].getdata()==0){
                            ss[j]=s2;
                            break;
                        }
                    }
                }
            }
            index1=-1;
            index2=-1;
            flag1=true;
            flag2=true;
        }
        ss[cal.final_index].TRecursion(ss[cal.final_index]);

    }
}

class Maths{
    int final_index=-1;
    float[] Findsmall(float[] num){//find the two smallest numbers and modify num[]
        float min1=num[0];
        float[] min = new float[2];
        int a=0, j=0, i;
        while(j<2){
            for(i=0; i<num.length; i=i+1){
                if(min1>num[i]){
                    min1 = num[i];
                    a = i;
                }
            }
            num[a]=1;
            min[j]=min1;
            min1=1;
            j=j+1;
        }
        num[a]=min[0]+min[1];
        return min;
    }

    boolean whetherstop(BinTree [] ss, int num){
        for(int i=0; i<num; i = i+1){
            if (ss[i].getdata() == (float)1){
                final_index=i;
                return false;
            }
        }
        return true;
    }
}