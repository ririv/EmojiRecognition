package com.riri.emojirecognition.component.deeplearning;

import org.tensorflow.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ClassifierWithTensorFlow {
    public void classify() {
        SavedModelBundle b = SavedModelBundle.load("C:\\Users\\Jean\\Desktop\\design\\EmojiRecognition\\src\\main\\resources\\model\\tf_model", "train");
        Session tfSession = b.session();
        Operation operationPredict = b.graph().operation("predict");   //要执行的op
        Output output = new Output(operationPredict, 0);
        //构造测试数据，用的是mnist测试集的第15个， mnist.test.images[15]，label是数字5
        float[][] a = new float[1][784];
        a[0] = new float[]{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.2f,0.517647f,0.839216f,0.992157f,0.996078f,0.992157f,0.796079f,0.635294f,0.160784f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.4f,0.556863f,0.796079f,0.796079f,0.992157f,0.988235f,0.992157f,0.988235f,0.592157f,0.27451f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.996078f,0.992157f,0.956863f,0.796079f,0.556863f,0.4f,0.321569f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.67451f,0.988235f,0.796079f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.0823529f,0.87451f,0.917647f,0.117647f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.478431f,0.992157f,0.196078f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.482353f,0.996078f,0.356863f,0.2f,0.2f,0.2f,0.0392157f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.0823529f,0.87451f,0.992157f,0.988235f,0.992157f,0.988235f,0.992157f,0.67451f,0.321569f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.0823529f,0.839216f,0.992157f,0.796079f,0.635294f,0.4f,0.4f,0.796079f,0.87451f,0.996078f,0.992157f,0.2f,0.0392157f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.239216f,0.992157f,0.670588f,0f,0f,0f,0f,0f,0.0784314f,0.439216f,0.752941f,0.992157f,0.831373f,0.160784f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.4f,0.796079f,0.917647f,0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.0784314f,0.835294f,0.909804f,0.321569f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.243137f,0.796079f,0.917647f,0.439216f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.0784314f,0.835294f,0.988235f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.6f,0.992157f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.160784f,0.913726f,0.831373f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.443137f,0.360784f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.121569f,0.678431f,0.956863f,0.156863f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.321569f,0.992157f,0.592157f,0f,0f,0f,0f,0f,0f,0.0823529f,0.4f,0.4f,0.717647f,0.913726f,0.831373f,0.317647f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.321569f,1.0f,0.992157f,0.917647f,0.596078f,0.6f,0.756863f,0.678431f,0.992157f,0.996078f,0.992157f,0.996078f,0.835294f,0.556863f,0.0784314f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.278431f,0.592157f,0.592157f,0.909804f,0.992157f,0.831373f,0.752941f,0.592157f,0.513726f,0.196078f,0.196078f,0.0392157f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0.0f};
        Tensor input_x = Tensor.create(a);
        List<Tensor<?>> out = tfSession.runner().feed("input_x", input_x).fetch(output).run();
        for (Tensor s : out) {
            float[][] t = new float[1][10];
            s.copyTo(t);
            for (float i : t[0])
                System.out.println(i);
        }
    }

    public void predict() throws Exception {
        try (Graph graph = new Graph()) {
            graph.importGraphDef(Files.readAllBytes(Paths.get(
                    "C:\\Users\\Jean\\Desktop\\design\\EmojiRecognition\\src\\main\\resources\\model\\tf_model\\saved_model.pb"
            )));
            try (Session sess = new Session(graph)) {
                // 自己构造一个输入
                float[][] input = {{56, 632, 675, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
                try (Tensor x = Tensor.create(input);
                     // input是输入的name，output是输出的name
                     Tensor y = sess.runner().feed("input", x).fetch("output").run().get(0)) {
                    float[][] result = new float[1][(int)y.shape()[1]];
                    y.copyTo(result);
                    System.out.println(Arrays.toString(y.shape()));
                    System.out.println(Arrays.toString(result[0]));
                }
            }
        }
    }
}
