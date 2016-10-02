package inoutfilesystem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Objects;

 /*
  * метод, который из переданного массива байт восстановит массив объектов Animal. 
  * Массив байт устроен следующим образом. Сначала идет число типа int, 
  * записанное при помощи ObjectOutputStream.writeInt(size). 
  * Далее подряд записано указанное количество объектов типа Animal, 
  * сериализованных при помощи ObjectOutputStream.writeObject(animal).
  * 
  * Если вдруг массив байт не является корректным представлением массива экземпляров Animal, 
  * то метод должен бросить исключение java.lang.IllegalArgumentException.
  */
public class Animal implements Serializable {

	private final String name;
	
	public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }
    
    public static Animal[] deserializeAnimalArray(byte[] data) {
    	int size = 0;
    	InputStream inputStream = new ByteArrayInputStream(data);
    	
    	ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(inputStream);
		} catch (IOException e) {
			throw new java.lang.IllegalArgumentException();
		}
    	try {
			size = ois.readInt();
		} catch (IOException e) {
			throw new java.lang.IllegalArgumentException();
		}
    	
    	Animal[] animals = new Animal[size];
    	for (int i= 0; i < size; i++) {
    		try {
				animals[i] = (Animal) ois.readObject();
    		} catch (ClassCastException e) {
				throw new java.lang.IllegalArgumentException();	
			} catch (ClassNotFoundException e) {
				throw new java.lang.IllegalArgumentException();
			} catch (IOException e) {
				throw new java.lang.IllegalArgumentException();
			}    		
    	}
		return animals;
    }
    	
	public String getName() {
		return name;
	}

	public static void main(String[] args) throws Exception {
		Animal dog = new Animal("Sharik");
		Animal cat = new Animal("Tom");
		Animal mouse = new Animal("Jerry");
		Animal bear = new Animal("Balu");
		Animal[] animals = new Animal[]{dog, cat, mouse};
		
		ByteArrayOutputStream outputByteStream = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(outputByteStream);
		oos.writeInt(3);
		for (Animal animal : animals){
		    oos.writeObject(animal);
		}
		byte[] data = outputByteStream.toByteArray();
		
		Animal[] animals2 = null;
		
		animals2 = deserializeAnimalArray(data);
		
		for (Animal animal : animals2){
		    System.out.println(animal.getName());
		}
	}

}
