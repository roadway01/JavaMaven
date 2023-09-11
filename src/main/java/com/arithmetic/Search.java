package com.arithmetic;

/**
 * @desc  : 常用查找方法实现
 * @author: Zhu
 * @date  : 2017年1月11日
 */
public class Search { 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[]{0,4,6,6,16,20,34,35,42,45,46,50,56,66,69,70,70,71,75,78};
		
		int location = -1;
		
		//location = Search.binarySearch(arr.clone(), 34);
		location = Search.binarySearch(arr.clone(), 34, 0, arr.length - 1);
		
		System.out.println(location + 1);
		
		Search search = new Search();
		
		BinaryNode bn = new BinaryNode(arr[0]);
		for(int i = 1; i < arr.length; i++){
			bn.add(arr[i]);
		}
		
		search.midOrder(bn);
		System.out.println();
		search.preOrder(bn);
		System.out.println();
		search.postOrder(bn);
	}
	
	/**
	 * @desc : 二分查找
	 * @date : 2017年1月11日
	 */
	public static int binarySearch (int arr[], int des){
		int low = 0;
		int high = arr.length - 1;
		
		while(low <= high){
			int middle = (low + high) / 2;
			if(des == arr[middle]){
				return middle;
			}else if(des < arr[middle]){
				high = middle - 1;
			}else{
				low = middle + 1;
			}
		}
		
		return -1;
	}
	
	/**
	 * @desc : 二分查找
	 * @date : 2017年1月11日
	 */
	public static int binarySearch(int arr[], int des, int begin, int end){
		
		int middle = (begin + end) / 2;
		if(des > arr[end] || des < arr[begin] || arr[end] < arr[begin]){
			return -1;
		}
		
		if(des < arr[middle]){
			return binarySearch(arr, des, begin, middle - 1);
		}else if(des > arr[middle]){
			return binarySearch(arr, des, middle + 1, end);
		}else{
			return middle;
		}
	}
	
	
	/**
	 * @desc : 中序遍历
	 * @date : 2017年1月12日
	 */
	public void midOrder(BinaryNode bn){
		if(bn == null)
			return;
		this.midOrder(bn.left);
		System.out.printf("%2d  ", bn.value);
		this.midOrder(bn.right);
	}
	
	/**
	 * @desc : 先序遍历
	 * @date : 2017年1月12日
	 */
	public void preOrder(BinaryNode bn){
		if(bn == null)
			return;
		
		System.out.printf("%2d  ", bn.value);
		this.preOrder(bn.left);
		this.preOrder(bn.right);
	}
	
	/**
	 * @desc : 后序遍历
	 * @date : 2017年1月12日
	 */
	public void postOrder(BinaryNode bn){
		if(bn == null)
			return;
		
		this.postOrder(bn.left);
		this.postOrder(bn.right);
		System.out.printf("%2d  ", bn.value);
	}
}

/**
 * @desc  : 定义二叉树
 * @author: Zhu
 * @date  : 2017年1月12日
 */
class BinaryNode{
	int value;
	BinaryNode left;
	BinaryNode right;
	
	public BinaryNode(int value){
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * @desc : 添加节点
	 * @date : 2017年1月12日
	 */
	public void add(int value){
		if(value > this.value){
			if(this.right != null){
				this.right.add(value);
			}else{
				this.right = new BinaryNode(value);
			}
		}else{
			if(this.left != null){
				this.left.add(value);
			}else{
				this.left = new BinaryNode(value);
			}
		}
	}
	
	/**
	 * @desc : 中序查找
	 * @date : 2017年1月12日
	 */
	public BinaryNode midGet(int value){
		if(this.value == value){
			return this;
		}
		
		if(value > this.value){
			return this.right.midGet(value);
		}
		
		if(value < this.value){
			return this.left.midGet(value);
		}
		
		return null;
	}

}


