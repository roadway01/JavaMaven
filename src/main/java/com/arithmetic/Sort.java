package com.arithmetic;

/**
 * @Name  :Sort
 * @Desc  :常用排序实现
 * @author:zhu
 * @date  :2016年11月22日
 */
public class Sort {
	final static int MAX = 20;

	public static void main(String args[]){
		//用于排序的数组
		int arr[] = new int[MAX];
		//生成随机数组数据
		System.out.println("生成的随机数组：");
		
		for(int i = 0; i < MAX; i++){
			arr[i] = (int) (Math.random()*100);
			System.out.printf("%2d  ", arr[i]);
		}
		
		System.out.println();

		//用于存放合并排序法中被合并排序好的数组 
		int arr3[]=new int[MAX + MAX];
		
		Sort sort = new Sort();
		sort.selSort(arr.clone());
		sort.insertSort(arr.clone());
		sort.bubSort(arr.clone());
		sort.shellSort(arr.clone());
		sort.shakeSort(arr.clone());
		sort.heapSort(arr.clone());
		sort.quickSort(arr.clone());
		
		//用于排序的数组--只用于合并排序法中
		int arr2[] = new int[MAX];
		//生成随机数组数据
		System.out.println("合并排序法需求数组2：");
		
		for(int i = 0; i < MAX; i++){
			arr2[i] = (int) (Math.random()*100);
			System.out.printf("%2d  ", arr2[i]);
		}
		
		System.out.println();
		
		sort.margeSort(arr.clone(), arr2, arr3);
		
		sort.baseSort(arr.clone());
	}
	
	/**
	 * @Desc  :快速排序:将要排序的对象分作两部份，一个是已排序的，一个是未排序的，从后端未排序部份选择一个最小值，
	 * 并放入前端已排序部份的最后一个。
	 * @param :
	 * @throws:
	 */
	public void selSort(int num[]){
		int m,temp;
		long start,end;
		
		start = System.nanoTime();
		for(int i = 0; i < MAX; i++){
			m = i;
			for(int j = i + 1; j < MAX; j++){
				if(num[j] < num[m]){
					m = j;
				}
			}
			
			if(i != m){
				temp = num[m];
				num[m] = num[i];
				num[i] = temp;
			}
		}
		end = System.nanoTime();
		
		System.out.println("=============================选择排序=============================");
		System.out.println("排序后结果：");
		for(int i = 0; i < MAX; i++){
			System.out.printf("%2d  ", num[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
	}
	
	/**
	 * @Desc  :插入排序:像是玩朴克一样，我们将牌分作两堆，每次从后面一堆的牌抽出最前端的牌，
	 * 			然后插入前面一堆牌的适当位置
	 * @param :
	 * @throws:
	 */
	public void insertSort(int num[]){
		int temp;
		long start,end;
		
		start = System.nanoTime();
		for(int i = 1; i < MAX; i++){
			temp = num[i];
			int j = i - 1;
			while(temp < num[j]){
				num[j + 1] = num[j];
				j--;
				
				if(j < 0){
					break;
				}
			}
			num[j + 1] = temp;
		}
		end = System.nanoTime();
		
		System.out.println("=============================插入排序=============================");
		System.out.println("排序后结果：");
		for(int i = 0; i < MAX; i++){
			System.out.printf("%2d  ", num[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
	}
	
	/**
	 * @Desc  :冒泡排序:基本的气泡排序法可以利用旗标的方式稍微减少一些比较的时间，当寻访完阵列后都没有发生任何的交换动作， 
         	      表示排序已经完成，而无需再进行之后的回圈比较与交换动作。
	 * @param :
	 * @throws:
	 */
	public void bubSort(int num[]){
		int temp,flag = 1;
		long start,end;
		
		start = System.nanoTime();
		for(int i = 0; i < MAX - 1 && flag == 1; i++){
			flag = 0;
			for(int j = 0; j < MAX - i - 1; j++){
				if(num[j + 1] < num[j]){
					temp = num[j];
					num[j] = num[j + 1];
					num[j + 1] = temp;
					flag = 1;
				}
			}
		}
		end = System.nanoTime();
		
		System.out.println("=============================冒泡排序=============================");
		System.out.println("排序后结果：");
		for(int i = 0; i < MAX; i++){
			System.out.printf("%2d  ", num[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
	}
	
	/**
	 * @Desc  :shell（希尔）排序法:Shell首先将间隔设定为n/2，然后跳跃进行插入排序，再来将间隔n/4，跳跃进行排序动作，
	 * 			再来间隔设定为n/8、n/16，直到间隔为1之后的最后一次排序终止，由于上一次的排序动作都会将 
    			固定间隔内的元素排序好，所以当间隔越来越小时，某些元素位于正确位置的机率越高，因此 
    			最后几次的排序动作将可以大幅减低。 
	 * @param :
	 * @throws:
	 */
	public void shellSort(int num[]){
		int gap,temp;
		long start,end;
		
		start = System.nanoTime();
		gap = MAX / 2;
		while(gap > 0){
			for(int k = 0; k < gap; k++){
				for(int i = k + gap; i < MAX; i += gap){
					for(int j = i - gap; j >= k; j -= gap){
						if(num[j] > num[j + gap]){
							temp = num[j];
							num[j] = num[j + gap];
							num[j + gap] = temp;
						}else{
							break;
						}
					}
				}
			}
			gap /= 2;
		}
		end = System.nanoTime();
		
		System.out.println("=============================shell（希尔）排序=============================");
		System.out.println("排序后结果：");
		for(int i = 0; i < MAX; i++){
			System.out.printf("%2d  ", num[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
	}
	
	/**
	 * @Desc  :Shake排序法（改良的冒泡排序法）:方法就在于气泡排序的双向进行，先让气泡排序由左向右进行，再来让气泡排序由右往左进行， 
              	如此完成一次排序的动作，而您必须使用left与right两个旗标来记录左右两端已排序的元素位置。
	 * @param :
	 * @throws:
	 */
	public void shakeSort(int num[]){
		int temp,left = 0,right = MAX - 1,shift = 0;
		long start,end;
		
		start = System.nanoTime();
		while(left < right){
			for(int i = left; i < right; i++){
				if(num[i] > num[i + 1]){
					temp = num[i + 1];
					num[i + 1] = num[i];
					num[i] = temp;
					shift = i;
				}
			}
			
			right = shift;
			
			for(int i = right; i > left; i--){
				if(num[i] < num[i - 1]){
					temp = num[i - 1];
					num[i - 1] = num[i];
					num[i] = temp;
					shift = i;
				}
			}
			
			left = shift;
		}
		end = System.nanoTime();
		
		System.out.println("=============================Shake排序=============================");
		System.out.println("排序后结果：");
		for(int i = 0; i < MAX; i++){
			System.out.printf("%2d  ", num[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
	}
	
	/**
	 * @Desc  :构造最大堆积树
	 * @param :
	 * @throws:
	 */
	public void createHeap(int num[]){
		int s,p,temp;
		int heap[] = new int[MAX + 1];
		for(int i = 1; i <= MAX; i++){
			heap[i] = num[i];
			s = i;
			p = i / 2;
			while(s >= 2 && heap[s] > heap[p]){
				temp = heap[s];
				heap[s] = heap[p];
				heap[p] = temp;
				
				s = p;
				p = s / 2;
			}
		}
		
		for(int i = 1; i <= MAX; i++){
			num[i] = heap[i];
		}
	}
	
	/**
	 * @Desc  :heap排序（堆排序法--改进的选择排序）利用堆积树的原理，先构造一个堆积树，
	 * 然后将根节点与最后的叶子节点交换，并屏蔽掉最后一个叶子节点， 然后再将未被屏蔽的部分重新构造堆积树，
	 * 然后再重复上面的步骤，直到所有的数被按顺序排好。
	 * @param :
	 * @throws:
	 */
	public void heapSort(int num[]){
		int m,p,s,temp;
		long start,end;
		
		start = System.nanoTime();
		int num_tmp[] = new int[MAX + 1];
		for(int i = 1; i <= MAX; i++){
			num_tmp[i] = num[i - 1];
		}
		
		createHeap(num_tmp);
		
		m = MAX;
		while(m > 1){
			temp = num_tmp[1];
			num_tmp[1] = num_tmp[m];
			num_tmp[m] = temp;
			
			m--;
			p = 1;
			s = 2 * p;
			while(s <= m){
				if(s < m && num_tmp[s + 1] > num_tmp[s]){
					s ++;
				}
				if(num_tmp[p] >= num_tmp[s]){
					break;
				}
				temp = num_tmp[p];
				num_tmp[p] = num_tmp[s];
				num_tmp[s] = temp;
				
				p = s;
				s = 2 * p;
			}
		}
		for(int i = 1; i <= MAX; i++){
			num[i - 1] = num_tmp[i];
		}
		end = System.nanoTime();
		
		System.out.println("=============================heap排序=============================");
		System.out.println("排序后结果：");
		for(int i = 0; i < MAX; i++){
			System.out.printf("%2d  ", num[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
	}
	
	public void quickSort(int num[]){
		long start,end;
		int left,right;
		left = 0;
		right = MAX - 1;
		
		start = System.nanoTime();
		int num1[] = num.clone();
		quickSortOne(num1, left, right);
		end = System.nanoTime();
		
		System.out.println("=============================快速排序法（一）=============================");
		System.out.println("排序后结果：");
		for(int i = 0; i < MAX; i++){
			System.out.printf("%2d  ", num1[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
		
		start = System.nanoTime();
		int num2[] = num.clone();
		quickSortTwo(num2, left, right);
		end = System.nanoTime();
		
		System.out.println("=============================快速排序法（二）=============================");
		System.out.println("排序后结果：");
		for(int i = 0; i < MAX; i++){
			System.out.printf("%2d  ", num2[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
		
		start = System.nanoTime();
		int num3[] = num.clone();
		quickSortThree(num3, left, right);
		end = System.nanoTime();
		
		System.out.println("=============================快速排序法（三）=============================");
		System.out.println("排序后结果：");
		for(int i = 0; i < MAX; i++){
			System.out.printf("%2d  ", num3[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
	}
	
	/**
	 * @Desc  :快速排序法（一）:这边所介绍的快速演算如下：将最左边的数设定为轴，并记录其值为s 
								廻圈处理： 
								令索引i 从数列左方往右方找，直到找到大于s 的数 
								令索引j 从数列左右方往左方找，直到找到小于s 的数 
								如果i >= j，则离开回圈 
								如果i < j，则交换索引i与j两处的值 
								将左侧的轴与j 进行交换 
								对轴左边进行递回 
								对轴右边进行递回 
	 * @param :
	 * @throws:
	 */
	public void quickSortOne(int num[], int left, int right){
		int s,temp;

		if(left < right){
			s = num[left];
			int i = left;
			int j = right + 1;
			while(true){
				while(i + 1 < num.length && num[++i] < s);
				while(j - 1 > -1 && num[--j] > s);
				if(i >= j) break;
				temp = num[i];
				num[i] = num[j];
				num[j] = temp;
			}
			
			num[left] = num[j];
			num[j] = s;
			quickSortOne(num, left, j - 1);
			quickSortOne(num, j + 1, right);
		}
	}
	
	/**
	 * @Desc  :快速排序法（二）:在这个例子中，取中间的元素s作比较，同样的先得右找比s大的索引i，然后找比s小的 
							    索引j，只要两边的索引还没有交会，就交换i 与j 的元素值，这次不用再进行轴的交换了， 
							    因为在寻找交换的过程中，轴位置的元素也会参与交换的动作，例如： 
							    41 24 76 11 45 64 21 69 19 36 
							    首先left为0，right为9，(left+right)/2 = 4（取整数的商），所以轴为索引4的位置，比较的元素是 
							    45，您往右找比45大的，往左找比45小的进行交换： 
							    41 24 76* 11 [45] 64 21 69 19 *36 
							    41 24 36 11 45* 64 21 69 19* 76 
							    41 24 36 11 19 64* 21* 69 45 76 
							   [41 24 36 11 19 21] [64 69 45 76] 
							    完成以上之后，再初别对左边括号与右边括号的部份进行递回，如此就可以完成排序的目的。
	 * @param :
	 * @throws:
	 */
	public void quickSortTwo(int num[], int left, int right){
		int s,temp;
		
		if(left < right){
			s = num[(left + right) / 2];
			int i = left - 1;
			int j = right + 1;
			while(true){
				while(num[++i] < s);
				while(num[--j] > s);
				if(i >= j) break;
				
				temp = num[j];
				num[j] = num[i];
				num[i] = temp;
			}
			
			quickSortTwo(num, left, i - 1);
			quickSortTwo(num, j + 1, right);
		}
	}
	
	/**
	 * @Desc  :快速排序法（三）:先说明这个快速排序法的概念，它以最右边的值s作比较的标准，将整个数列分为三个部份， 
							    一个是小于s的部份，一个是大于s的部份，一个是未处理的部份，如下所示： 
							            i           j 
							    --------|-----------|----------|s 
							        小于s     大于s         未处理 
							    在排序的过程中，i 与j 都会不断的往右进行比较与交换，最后数列会变为以下的状态： 
							    -------------|-----------------|s 
							        小于s             大于s 
							    然后将s的值置于中间，接下来就以相同的步骤会左右两边的数列进行排序的动作，如下所示： 
							    -------------|s|--------------- 
							        小于s             大于s 
							    然后采用递归的方法重复上面的步骤，就可以实现排序了。
	 * @param :
	 * @throws:
	 */
	public void quickSortThree(int num[], int left, int right){
		int q,s,temp;
		
		if(left < right){
			s = num[right];
			int i = left - 1;
			for(int j = left; j < right; j++){
				if(num[j] <= s){
					i++;
					temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
			temp = num[i + 1];
			num[i + 1] = num[right];
			num[right] = temp;
			
			q = i + 1;
			quickSortThree(num, left, q - 1);
			quickSortThree(num, q + 1, right);
		}
	}
	
	/**
	 * @Desc  :合并排序法:合并排序法基本是将两笔已排序的资料合并并进行排序，如果所读入的资料尚未排序， 
					             可以先利用其它的排序方式来处理这两笔资料，然后再将排序好的这两笔资料合并。 
					             合并排序法中用到了  快速排序法（三）
	 * @param :
	 * @throws:
	 */
	public void margeSort(int num1[], int num2[], int num3[]){
		long start,end;
		
		start = System.nanoTime();
		quickSortThree(num1, 0, MAX - 1);
		quickSortThree(num2, 0, MAX - 1);
		
		int k = 0, i = 0, j = 0;
		while(i < MAX && j < MAX){
			if(num1[i] <= num2[j]){
				num3[k++] = num1[i++];
			}else{
				num3[k++] = num2[j++];
			}
		}
		
		while(i < MAX){
			num3[k++] = num1[i++];
		}
		
		while(j < MAX){
			num3[k++] = num2[j++];
		}
		end = System.nanoTime();
		
		System.out.println("=============================合并排序=============================");
		System.out.println("排序后结果：");
		for(i = 0; i < MAX * 2; i++){
			System.out.printf("%2d  ", num3[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
	}
	
	/**
	 * @Desc  :基数排序法:基数排序的方式可以采用LSD（Least sgnificant digital）或MSD（Most sgnificant digital）， 
						    LSD的排序方式由键值的最右边开始，而MSD则相反，由键值的最左边开始。 
						    以LSD为例，假设原来有一串数值如下所示： 
						    73, 22, 93, 43, 55, 14, 28, 65, 39, 81 
						    首先根据个位数的数值，在走访数值时将它们分配至编号0到9的桶子中： 
						    0   1   2   3   4   5   6   7   8   9 
						        81              65              39 
						                43  14  55          28 
						                93 
						            22  73 
						    接下来将这些桶子中的数值重新串接起来，成为以下的数列： 
						    81, 22, 73, 93, 43, 14, 55, 65, 28, 39 
						    接着再进行一次分配，这次是根据十位数来分配： 
						    接下来将这些桶子中的数值重新串接起来，成为以下的数列： 
						    0   1   2   3   4   5   6   7   8   9 
						            28  39 
						        14  22      43  55  65  73  81  93 
						    14, 22, 28, 39, 43, 55, 65, 73, 81, 93 
						    这时候整个数列已经排序完毕；如果排序的对象有三位数以上，则持续进行以上的动作直至最 
						    高位数为止。 
						    LSD的基数排序适用于位数小的数列，如果位数多的话，使用MSD的效率会比较好，MSD的方 
						    式恰与LSD相反，是由高位数为基底开始进行分配，其他的演算方式则都相同。
	 * @param :
	 * @throws:
	 */
	public void baseSort(int num[]){
		int temp[][] = new int[MAX][MAX];
		int order[] = new int[MAX];
		int k,n,lsd;
		long start,end;
		k = 0;
		n = 1;
		
		start = System.nanoTime();
		while(n <= 10){
			for(int i = 0; i < MAX; i++){
				lsd = (num[i] / n) % 10;
				temp[lsd][order[lsd]] = num[i];
				order[lsd]++;
			}
			
			for(int i = 0; i < MAX; i++){
				if(order[i] != 0){
					for(int j = 0; j < order[i]; j++){
						num[k++] = temp[i][j];
					}
				}
				order[i] = 0;
			}
			
			n *= 10;
			k = 0;
		}
		end = System.nanoTime();
		
		System.out.println("=============================基数排序=============================");
		System.out.println("排序后结果：");
		for(int i = 0; i < MAX; i++){
			System.out.printf("%2d  ", num[i]);
		}
		System.out.println("\n用时：" + (end - start) + "ns\n");
	}
	
}
