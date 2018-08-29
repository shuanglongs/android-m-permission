package shuanglong.android.m.permission.processors;

import com.google.auto.service.AutoService;

import java.lang.annotation.ElementType;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import shuanglong.android.m.permission.annotation.PermissionDenied;
import shuanglong.android.m.permission.annotation.PermissionGranted;
import shuanglong.android.m.permission.annotation.ShowRequestPermissionRationale;

@AutoService(Processor.class)
public class PermissionProcessor extends AbstractProcessor {

    public static final String TAG = "Permission_Processor";
    private Elements mElementUtils;
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        d(TAG, "init...");
        //返回用来在元素上进行操作的某些实用工具方法的实现。Elements是一个工具类，
        // 可以处理相关Element（包括ExecutableElement, PackageElement, TypeElement, TypeParameterElement, VariableElement）
        mElementUtils = processingEnvironment.getElementUtils();
        //用来创建新源、类或辅助文件的 Filer。
        mFiler = processingEnvironment.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationType = new LinkedHashSet<>();
        annotationType.add(PermissionGranted.class.getCanonicalName());
        annotationType.add(PermissionDenied.class.getCanonicalName());
        annotationType.add(ShowRequestPermissionRationale.class.getCanonicalName());
        return super.getSupportedAnnotationTypes();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(PermissionGranted.class);
        for (Element element : elementsAnnotatedWith){
            if (element.getKind() == ElementKind.CLASS){
                TypeElement typeElement = (TypeElement) element;
                String name = typeElement.getSimpleName().toString();
                PermissionGranted annotation = typeElement.getAnnotation(PermissionGranted.class);
                d(TAG,name);
                d(TAG,annotation.value()+"");
            }
        }
        return false;
    }


    private void d(String TAG, String info) {
        System.out.println(TAG + " --> " + info);
    }
}
